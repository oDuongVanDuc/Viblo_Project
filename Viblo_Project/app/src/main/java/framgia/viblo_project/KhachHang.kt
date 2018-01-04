package framgia.viblo_project

/**
 * Created by duong.van.duc on 1/3/18.
 */

data class KhachHang(var tenKh :String,var slMua:Int,var isVip: Boolean)
{
    val GIA:Int=20000
    fun tinhThanhTien(): Double {
        return if (!isVip) slMua * GIA.toDouble() else slMua.toDouble() * GIA.toDouble() * 0.9
    }
}

