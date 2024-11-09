# Dicoding Event App

![Dicoding Event App Banner](https://via.placeholder.com/1200x300.png?text=Dicoding+Event+App)

Aplikasi **Dicoding Event App** adalah aplikasi Android yang memungkinkan pengguna untuk menelusuri, mencari, dan melihat detail dari berbagai event. Aplikasi ini terhubung dengan API untuk menampilkan informasi event, serta dilengkapi dengan fitur favorit untuk menyimpan event yang diminati.

## Daftar Isi
- [Fitur](#fitur)
- [Screenshots](#screenshots)
- [Teknologi dan Library](#teknologi-dan-library)
- [Instalasi](#instalasi)
- [Cara Penggunaan](#cara-penggunaan)
- [Struktur Proyek](#struktur-proyek)
- [Kontribusi](#kontribusi)
- [Lisensi](#lisensi)

## Fitur
- **Menampilkan Event**: Daftar event yang sedang berlangsung atau yang akan datang dari API Dicoding.
- **Detail Event**: Informasi lengkap event termasuk waktu, lokasi, dan deskripsi.
- **Fitur Favorit**: Menyimpan event ke dalam daftar favorit pengguna.
- **Mode Tema**: Mendukung mode terang dan gelap yang disimpan menggunakan DataStore.
- **Navigasi yang Mudah**: Bottom Navigation dan Navigation Component untuk berpindah antar halaman.

## Screenshots
> Tambahkan screenshot atau GIF dari aplikasi di sini untuk menunjukkan tampilan utama aplikasi.

## Teknologi dan Library
Aplikasi ini dibuat dengan teknologi dan library berikut:
- **Kotlin**: Bahasa pemrograman utama.
- **Retrofit**: Mengambil data dari API.
- **Room Database**: Menyimpan data favorit secara lokal.
- **DataStore**: Menyimpan preferensi tema.
- **LiveData** dan **ViewModel**: Mengelola data UI yang sesuai dengan lifecycle.
- **Navigation Component**: Mengatur navigasi antar halaman.
- **Glide**: Memuat dan menampilkan gambar.

## Instalasi
1. Clone repository ini:
   ```bash
   git clone https://github.com/AznKuy/Dicoding-event-app.git
   ```
2. Buka proyek di Android Studio.
3. Sinkronisasi proyek dengan Gradle.
4. Jalankan aplikasi di emulator atau perangkat fisik.

## Cara Penggunaan
1. Buka aplikasi dan telusuri daftar event yang tersedia.
2. Klik pada event untuk melihat detail lebih lanjut.
3. Tambahkan event ke daftar favorit dengan ikon hati di halaman detail.
4. Ganti mode tema melalui pengaturan.

## Struktur Proyek
- **ui**: Berisi tampilan layar aplikasi, seperti fragment dan adapter untuk daftar event.
- **data**: Berisi kelas untuk mengakses API, database, dan sumber data lainnya.
- **di**: Pengaturan Dependency Injection.
- **model**: Model data dari API atau database lokal.

## Kontribusi
Kontribusi selalu diterima! Silakan buat _pull request_ atau laporkan _issue_.

1. Fork repository ini.
2. Buat _branch_ fitur (`git checkout -b fitur-baru`).
3. Commit perubahan Anda (`git commit -m 'Menambahkan fitur baru'`).
4. Push ke _branch_ (`git push origin fitur-baru`).
5. Buat _pull request_.
