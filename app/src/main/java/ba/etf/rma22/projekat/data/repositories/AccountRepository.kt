package ba.etf.rma22.projekat.data.repositories

class AccountRepository {
    companion object {
        var acHash = "39590664-6275-430a-ab51-a784d4546f62"
    }
    fun postaviHash(hash:String):Boolean{
        try{
            acHash=hash
            return true
        }catch(e : Exception){
            return false
        }
    }
    fun getHash():String{
            return acHash
    }

}