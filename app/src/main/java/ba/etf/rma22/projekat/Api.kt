package ba.etf.rma22.projekat


import android.app.Notification
import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.ApiConfig
import retrofit2.http.*

interface Api {
    @GET("/anketa/{id}/pitanja")
    suspend fun getPitanja(@Path("id") idKviza: Int): List<Pitanje>

    @POST("/student/{id}/anketa/{kid}")
    suspend fun zapocniAnketu(@Path("kid") idAnkete: Int, @Path("id") id: String): AnketaTaken

    @GET("/student/{id}/anketataken")
    suspend fun getPoceteAnkete(@Path("id") idStudenta: String): List<AnketaTaken>

    @GET("/student/{id}/anketataken/{ktid}/odgovori")
    suspend fun getOdgovoriAnketa(
        @Path("ktid") idAnkete: Int,
        @Path("id") hashStudenta: String
    ): List<Odgovor>

    @GET("/anketa")
    suspend fun getAll(@Query("offset") offset: Int): List<Anketa>

    @GET("/anketa/{id}")
    suspend fun getById(@Path("id") id: Int): Anketa

    @GET("/student/{id}/grupa")
    suspend fun getUpisaneGrupe(@Path("id") hashStudenta: String): List<Grupa>

    @GET("/grupa/{id}/ankete")
    suspend fun getAnketeZaGrupu(@Path("id") idGrupe: Int): List<Anketa>

    @GET("/grupa")
    suspend fun getGrupe(): List<Grupa>

    @GET("/grupa/{gid}/istrazivanje")
    suspend fun getIstrazivanjaZaGrupu(@Path("gid") idGrupe: Int): List<Istrazivanje>

    @POST("/grupa/{gid}/student/{id}")
    suspend fun upisiUGrupu(@Path("gid") idGrupe: Int, @Path("id") hash: String): Poruka

    @GET("/istrazivanje")
    suspend fun getIstrazivanja(@Query("offset") offset: Int): List<Istrazivanje>

    @GET("/istrazivanje/{id}")
    suspend fun getIstrazivanjeId(@Path("id") istrazivanjeId: Int): Istrazivanje

    @GET ("/anketa/{id}/grupa")
    suspend fun getGrupeZaAnketu(@Path("id") idAnkete : Int) : List<Grupa>

    @POST("/student/{id}/anketataken/{ktid}/odgovor")
    suspend fun postaviOdgovorAnketa(@Path("ktid") anketaId : Int, @Path("id") hash : String, @Body podaci: PovratniPodaci) : PovratniOdgovor
}