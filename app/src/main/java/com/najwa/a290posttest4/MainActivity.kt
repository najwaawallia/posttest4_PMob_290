package com.najwa.a290posttest4

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.najwa.a290posttest4.R


class MainActivity : AppCompatActivity() {

    private val daftarPenduduk: MutableList<Penduduk> = mutableListOf()

    private lateinit var etNamaLengkap: EditText
    private lateinit var etNIK: EditText
    private lateinit var etKabupaten: EditText
    private lateinit var etKecamatan: EditText
    private lateinit var etDesa: EditText
    private lateinit var etRT: EditText
    private lateinit var etRW: EditText
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var spStatusPernikahan: Spinner
    private lateinit var btnSimpan: Button
    private lateinit var btnReset: Button
    private lateinit var tvDaftarPenduduk: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initializeViews()


        btnSimpan.setOnClickListener {
            simpanDataPenduduk()
        }

        btnReset.setOnClickListener {
            resetDataForm()
        }


        updateDaftarPendudukView()
    }

    private fun initializeViews() {
        etNamaLengkap = findViewById(R.id.etNamaLengkap)
        etNIK = findViewById(R.id.etNIK)
        etKabupaten = findViewById(R.id.etKabupaten)
        etKecamatan = findViewById(R.id.etKecamatan)
        etDesa = findViewById(R.id.etDesa)
        etRT = findViewById(R.id.etRT)
        etRW = findViewById(R.id.etRW)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        spStatusPernikahan = findViewById(R.id.spStatusPernikahan)
        btnSimpan = findViewById(R.id.btnSimpan)
        btnReset = findViewById(R.id.btnReset)
        tvDaftarPenduduk = findViewById(R.id.tvDaftarWarga)
    }

    private fun simpanDataPenduduk() {
        val namaLengkap = etNamaLengkap.text.toString().trim()
        val nik = etNIK.text.toString().trim()
        val kabupaten = etKabupaten.text.toString().trim()
        val kecamatan = etKecamatan.text.toString().trim()
        val desa = etDesa.text.toString().trim()
        val rt = etRT.text.toString().trim()
        val rw = etRW.text.toString().trim()
        val selectedJenisKelaminId = rgJenisKelamin.checkedRadioButtonId
        val statusPernikahanSpinner = findViewById<Spinner>(R.id.spStatusPernikahan)
        val statusPernikahan = statusPernikahanSpinner.selectedItem.toString()

        if (namaLengkap.isEmpty() || nik.isEmpty() || kabupaten.isEmpty() || kecamatan.isEmpty() ||
            desa.isEmpty() || rt.isEmpty() || rw.isEmpty() || selectedJenisKelaminId == -1) {
            Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        // 1. Mendapatkan nilai Jenis Kelamin
        val jenisKelamin = findViewById<RadioButton>(selectedJenisKelaminId).text.toString()

        // 2. Buat objek Warga
        val pendudukBaru = Penduduk(namaLengkap, nik, kabupaten, kecamatan, desa, rt, rw, jenisKelamin, statusPernikahan)

        // 3. Simpan data (tambah ke list)
        daftarPenduduk.add(pendudukBaru)

        // 4. Reset form setelah berhasil menyimpan
        resetFormInputs()

        // 5. Update tampilan daftar penduduk
        updateDaftarPendudukView()
        // 6. Tampilkan notifikasi "Data berhasil disimpan!" (seperti di Page 2)
        Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
    }

    private fun resetFormInputs() {
        etNamaLengkap.setText("")
        etNIK.setText("")
        etKabupaten.setText("")
        etKecamatan.setText("")
        etDesa.setText("")
        etRT.setText("")
        etRW.setText("")
        rgJenisKelamin.clearCheck()
        spStatusPernikahan.setSelection(0)
    }

    private fun resetDataForm() {
        // Hapus semua data
        daftarPenduduk.clear()

        // Reset input form
        resetFormInputs()

        // Update tampilan daftar warga
        updateDaftarPendudukView()

        // Tampilkan notifikasi "Semua data berhasil dihapus!"
        Toast.makeText(this, "Semua data berhasil dihapus!", Toast.LENGTH_SHORT).show()
    }

    private fun updateDaftarPendudukView() {
        if (daftarPenduduk.isEmpty()) {
            tvDaftarPenduduk.text = "Belum ada data warga yang tersimpan."
        } else {
            val builder = StringBuilder()
            daftarPenduduk.forEachIndexed { index, penduduk ->
                builder.append("${index + 1}. ${penduduk.namaLengkap} ${penduduk.toString()}\n")

                if (index < daftarPenduduk.size - 1) {
                    builder.append("\n")
                }
            }
            tvDaftarPenduduk.text = builder.toString()
        }
    }


}