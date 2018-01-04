package framgia.viblo_project

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var danhsach = DanhSachKhachHang()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addEvents()
    }

    private fun addEvents() {
        btntinhtt.setOnClickListener(ProcessMyEvent())
        btntiep.setOnClickListener(ProcessMyEvent())
        btnthongke.setOnClickListener(ProcessMyEvent())
        imgbtnthoat.setOnClickListener(ProcessMyEvent())
    }

    private fun doTinhTien() {
        var slSach = if(editsoluongsach.text.toString().equals("")) "0" else editsoluongsach.text.toString()
        Log.d("TAG","Ten KH : ${edittenkh.text}")
        Log.d("TAG","SL Mua $slSach")
        Log.d("TAG","Da Check ${chklavip.isChecked}")
        val kh = KhachHang(edittenkh.text.toString(),slSach.toInt(),chklavip.isChecked)
        txtthanhtien.setText(kh.tinhThanhTien().toString() + "")
        danhsach.addKhachHang(kh)
    }

    private fun doTiep() {
        edittenkh.setText("")
        editsoluongsach.setText("")
        txtthanhtien.setText("")
        edittenkh.requestFocus()
    }

    private fun doThongKe() {
        edittongsokh.setText(danhsach.tongKhachHang().toString())
        edittongsokhlavip.setText(danhsach.tongKhachHangVip().toString())
        edittongdt.setText(danhsach.tongDoanhThu().toString())
    }

    private fun doThoat() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("hỏi thoát chương trình")
        builder.setMessage("Muốn thoát chương trình này hả?")
        builder.setNegativeButton("Không") { dialog, which -> dialog.cancel() }
        builder.setPositiveButton("Có") { dialog, which -> finish() }
        builder.create().show()
    }

    private inner class ProcessMyEvent : View.OnClickListener {
        override fun onClick(arg0: View) {
            when (arg0.id) {
                R.id.btntinhtt -> doTinhTien()
                R.id.btntiep -> doTiep()
                R.id.btnthongke -> doThongKe()
                R.id.imgbtnthoat -> doThoat()
            }
        }

    }
}



