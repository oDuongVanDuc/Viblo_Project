package framgia.viblo_project

import java.util.ArrayList

/**
 * Created by duong.van.duc on 1/3/18.
 */
class DanhSachKhachHang {
    internal var listKH = ArrayList<KhachHang>()
    fun addKhachHang(kh: KhachHang) {
        listKH.add(kh)
    }

    fun tongDoanhThu(): Double {
        var tien = 0.0
        for (kh in listKH) {
            tien += kh.tinhThanhTien()
        }
        return tien
    }

    fun tongKhachHang(): Int {
        return listKH.size
    }

    fun tongKhachHangVip(): Int {
        var s = 0
        for ((_, _, isVip) in listKH) {
            if (isVip)
                s++
        }
        return s
    }
}