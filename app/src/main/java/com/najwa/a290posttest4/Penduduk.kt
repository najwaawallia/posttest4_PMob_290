package com.najwa.a290posttest4

data class Penduduk(
    val namaLengkap: String,
    val nik: String,
    val kabupaten: String,
    val kecamatan: String,
    val desa: String,
    val rt: String,
    val rw: String,
    val jenisKelamin: String,
    val statusPernikahan: String
) {
    override fun toString(): String {
        return "(${jenisKelamin}) - ${statusPernikahan}\n" +
                "NIK: ${nik}\n" +
                "Alamat: RT ${rt}/RW ${rw}, ${desa}, ${kecamatan}, ${kabupaten}"
    }
}