@file:Suppress("unused", "RedundantSuppression", "RedundantSuppression", "RedundantSuppression")

package com.example.dicodingeventapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.dicodingeventapp.R
import com.example.dicodingeventapp.data.database.FavoriteEvent
import com.example.dicodingeventapp.data.remote.response.Event
import com.example.dicodingeventapp.databinding.ActivityDetailBinding
import com.example.dicodingeventapp.ui.favorite.FavoriteViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val eventDetail: DetailViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val urlBaseRegis = "https://www.dicoding.com/events/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventId = intent.getIntExtra("eventId", -1)
        if (eventId != -1) {
            eventDetail.getEventById(eventId)

            eventDetail.isLoading.observe(this) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

            }

            eventDetail.eventDetail.observe(this) { event ->
                if (event != null) {
                    bindEventDetails(event)
                    setupRegisterButton(eventId)
                    setupFavoriteButton(event)
                } else {
                    Log.e("DetailActivity", "Failed to receive event details")
                    Toast.makeText(this, "Failed to load event details", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            Log.e("DetailActivity", "Invalid event ID")
            finish()
        }
    }

    private fun setupFavoriteButton(event: Event) {
        event.id?.let { id ->
            favoriteViewModel.isEventFavorite(id).observe(this) { isFavorite ->
                isFavorite?.let { favorite ->
                    updateFabIcon(favorite)
                    binding.fabFavorite.setOnClickListener {
                        if (favorite) {
                            favoriteViewModel.deleteFavoriteEvent(id)
                            Toast.makeText(this, "Event dihapus dari favorit", Toast.LENGTH_SHORT).show()
                        } else {
                            favoriteViewModel.addFavoriteEvent(
                                FavoriteEvent(
                                    id = id.toString(),
                                    name = event.name,
                                    mediaCover = event.mediaCover,
                                    category = event.category
                                )
                            )
                            Toast.makeText(this, "Event ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                        }
                        updateFabIcon(!favorite)
                    }
                } ?: run {
                    Log.e("DetailActivity", "Failed to check if event is favorite")
                }
            }
        } ?: run {
            Toast.makeText(this, "Gagal menambahkan event ke favorit", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFabIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_fill)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_bor)
        }
    }

    private fun setupRegisterButton(eventId: Int) {
        val btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val fullUrl = "$urlBaseRegis$eventId"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
            startActivity(intent)
        }
    }

    private fun bindEventDetails(event: Event) {
        with(binding) {
            Log.d("DetailActivity", "Binding event details: $event")
            Glide.with(this@DetailActivity)
                .load(event.mediaCover)
                .into(imgEvent)
            tvEventName.text = event.name
            tvEventOwner.text = event.ownerName
            tvEventBegin.text = formatDate(event.beginTime)
//            tvEventEnd.text = formatDate(event.endTime)
            tvEventLocation.text = event.cityName
            tvDescription.text = HtmlCompat.fromHtml(
                event.description.toString(),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            tvSummary.text = event.summary

            // Menkalinan quota
            val availableQuota = event.registrants?.let { event.quota?.minus(it) }
            tvQuota.text = "Sisa Kuota Pendaftaran: $availableQuota"

            // Jika event sudah berakhir
            if (hasEventEnded(event.endTime)) {
                btnRegister.text = "Event Telah Berakhir"
            }

            // Jika kuota pendafataran sudah habis
            if (availableQuota == 0) {
                Toast.makeText(this@DetailActivity, "Kuota pendaftaran sudah habis", Toast.LENGTH_SHORT).show()
                btnRegister.text = "Kuota Pendaftaran Habis"
            } else {
                btnRegister.isEnabled = true
            }
        }
    }

    private fun hasEventEnded(endTime: String?): Boolean {
        if (endTime.isNullOrEmpty()) return false

        return try {
            val originalFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            originalFormat.timeZone = TimeZone.getTimeZone("UTC")
            val eventEndDate = originalFormat.parse(endTime)

            val currentDate = Date()
            eventEndDate?.before(currentDate) ?: false
        } catch (e: Exception) {
            Log.e("DateCheck", "Error checking event end date: $e")
            false
        }
    }

    private fun formatDate(dateString: String?): String {
        if (dateString.isNullOrEmpty()) return ""
        return try {
            val originalFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val targetFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            val date = originalFormat.parse(dateString)
            date?.let { targetFormat.format(it) } ?: ""
        } catch (e: Exception) {
            Log.e("DateFormat", "Error formatting date: $e")
            ""
        }
    }
}
