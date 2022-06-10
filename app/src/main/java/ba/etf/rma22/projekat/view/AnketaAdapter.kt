package ba.etf.rma22.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors


class AnketaAdapter(
    private var ankete: List<Anketa>
)  : RecyclerView.Adapter<AnketaAdapter.AnketaViewHolder>(){

    private lateinit var msListener : onItemClickListener
    private var status: String? = null
    interface onItemClickListener{
        fun onItemClick(position : Int){

        }
    }
    fun setOnItemClickListener(listener : onItemClickListener){
        msListener=listener
    }
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_anketa, parent, false)
        return AnketaViewHolder(view, msListener)
    }

    override fun getItemCount(): Int = ankete.size

    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        holder.nazivAnkete.text = ankete[position].naziv
        holder.istrazivanje.text = ankete[position].nazivIstrazivanja
        val formatDatuma = SimpleDateFormat("dd.MM.yyyy")
        var pr1: Float = ankete[position].progres!! * 100
        pr1= Math.round(pr1).toFloat()
        var pr: Int = pr1.toInt()
        if(pr%2!=0){
            pr += 10
        }

        holder.progressBar.setProgress(pr)
        //if(ankete[position].datumPocetak==null || ankete[position].datumKraj==null) {
        //    holder.txtViewZaDatum.text =
          //      "Anketa null: " //+ formatDatuma.format(ankete[position].datumKraj)
           // holder.anketaImage.setImageResource(R.drawable.crvena)

        //}
        /*else if (ankete[position].datumRada!=null /*&& ankete[position].datumKraj.after(ankete[position].datumRada)*/) {
            holder.txtViewZaDatum.text = "Anketa urađena: " //+ formatDatuma.format(ankete[position].datumRada)
            holder.anketaImage.setImageResource(R.drawable.plava)
        } *//*else if (ankete[position].datumRada == null && ankete[position].datumPocetak.before(
                Calendar.getInstance().time) && ankete[position].datumKraj.after(
                Calendar.getInstance().time)
        ) {
            holder.txtViewZaDatum.text =
                "Vrijeme zatvaranja: " + formatDatuma.format(ankete[position].datumKraj)
            holder.anketaImage.setImageResource(R.drawable.zelena)

        } else if (ankete[position].datumPocetak.after(Calendar.getInstance().time) ) {
            holder.txtViewZaDatum.text =
                "Vrijeme aktiviranja: " + formatDatuma.format(ankete[position].datumPocetak)
            holder.anketaImage.setImageResource(R.drawable.zuta)
        }
        else if(ankete[position].datumRada == null && ankete[position].datumKraj.before(Calendar.getInstance().time)){
            holder.txtViewZaDatum.text =
                "Anketa zatvorena: " + formatDatuma.format(ankete[position].datumKraj)
            holder.anketaImage.setImageResource(R.drawable.crvena)

        }*/
    }

    fun updateAnkete(ankete1: List<Anketa>) {
        ankete = ankete1
        notifyDataSetChanged()
    }

    inner class AnketaViewHolder(itemView: View, listener : AnketaAdapter.onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val anketaImage: ImageView = itemView.findViewById(R.id.imageView)
        val txtViewZaDatum: TextView = itemView.findViewById(R.id.datum)
        val nazivAnkete: TextView =itemView.findViewById(R.id.nazivAnkete)
        val istrazivanje: TextView =itemView.findViewById(R.id.brojIstrazivanja)
        val progressBar: ProgressBar =itemView.findViewById(R.id.progresZavrsetka)
        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
    }

}


}
/*
class AnketaAdapter(
    private var quizzes: List<Anketa>,
    private val onItemClicked: (kviz: Anketa) -> Unit
): RecyclerView.Adapter<AnketaAdapter.AnketaViewHolder>(){

    private val kvizViewModel = AnketaViewModel()
    private var status: String? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnketaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_anketa, parent, false)
        return AnketaViewHolder(view)
    }

    override fun getItemCount(): Int = quizzes.size

    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {

        holder.itemView.setOnClickListener{onItemClicked(quizzes[position])}

      //  holder.quizName.text = quizzes[position].naziv
        // nije radio kviz
        if(quizzes[position].datumRada == null){
            // nije aktivan kviz onda datum pocetka inace datum kraja
            if(uporediSaTrenutnimDatumom(quizzes[position].datumPocetak) == 1 || quizzes[position].datumKraj == null)
                holder.txtViewZaDatum.text = dajDatum(quizzes[position].datumPocetak)
            else holder.txtViewZaDatum.text = quizzes[position].datumKraj?.let { dajDatum(it) }
        }
        else holder.txtViewZaDatum.text = quizzes[position].datumRada?.let { dajDatum(it) }
      //  holder.quizSubjectName.text = quizzes[position].nazivPredmeta
        //holder.quizTime.text = quizzes[position].trajanje.toString() + " min"

        postaviStatus(quizzes[position], holder, position)
        // while(status == null);

    }

    private fun dajDatum(datumRada: Date): String{
        val dan: Int = datumRada.getDate()
        var mjesec: Int = datumRada.getMonth() +1
        val danString: String?
        val mjesecString: String?
        if(dan < 10){
            danString = "0" + dan.toString() + "."
        }
        else danString = dan.toString() + "."
        if(mjesec < 10){
            mjesecString = "0" + mjesec.toString() + "."
        }
        else mjesecString = mjesec.toString() + "."

        return danString + mjesecString + datumRada.getYear().plus(1900).toString()
    }

    private fun uporediSaTrenutnimDatumom(datum1: Date): Int{
        var godina = Calendar.getInstance().get(Calendar.YEAR)
        var mjesec = Calendar.getInstance().get(Calendar.MONTH) + 1
        var dan = Calendar.getInstance().get(Calendar.DATE)
        if(datum1.getYear().plus(1900) > godina) return 1
        else if(godina > datum1.getYear().plus(1900)) return 2;
        else if(datum1.getMonth().plus(1) > mjesec) return 1;
        else if(mjesec > datum1.getMonth().plus(1)) return 2;
        else if(datum1.getDate() > dan) return 1;
        else if(dan > datum1.getDate()) return 2;
        return 0;
    }


    private fun postaviStatus(anketa : Anketa, holder: AnketaViewHolder, position: Int) {
       // kvizViewModel.getSveAnkete(anketa, holder, position, onSuccess = ::onSuccess, onError = ::onError)
    }


    fun updateQuizes(quizzes: List<Anketa>) {
        this.quizzes = quizzes
        // sortiranje kvizova
        this.quizzes = this.quizzes.stream().sorted { o1, o2 -> uporediDatume(o1, o2)}.collect(
            Collectors.toList())
        //
        notifyDataSetChanged()
    }

    private fun uporediDatume(o1: Anketa, o2: Anketa): Int {
        if(o1.datumPocetak.getYear() > o2.datumPocetak.getYear()) return 1
        else if(o2.datumPocetak.getYear() > o1.datumPocetak.getYear()) return -1;
        else if(o1.datumPocetak.getMonth() > o2.datumPocetak.getMonth()) return 1;
        else if(o2.datumPocetak.getMonth() > o1.datumPocetak.getMonth()) return -1;
        else if(o1.datumPocetak.getDate() > o2.datumPocetak.getDate()) return 1;
        else if(o2.datumPocetak.getDate() > o1.datumPocetak.getDate()) return -1;
        return 0;
    }

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val anketaImage: ImageView = itemView.findViewById(R.id.imageView)
        val txtViewZaDatum: TextView = itemView.findViewById(R.id.datum)
        val nazivAnkete: TextView =itemView.findViewById(R.id.nazivAnkete)
        val istrazivanje: TextView =itemView.findViewById(R.id.brojIstrazivanja)
        val progressBar: ProgressBar =itemView.findViewById(R.id.progresZavrsetka)
    }

    fun onSuccess(anketa: Anketa, rezultat: Boolean, holder: AnketaAdapter.AnketaViewHolder, position: Int){
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                if(rezultat){
                    status = "plava"
                }
                else if(anketa.datumRada == null){
                    var datumPocetka = uporediSaTrenutnimDatumom(anketa.datumPocetak)
                    var datumKraja = anketa.datumKraj?.let { uporediSaTrenutnimDatumom(it) }
                    // kviz nije otvoren
                    if(datumPocetka == 1){
                        status = "zuta"
                    }
                    // kviz aktivan
                    else if(datumPocetka == 2 && (datumKraja == 1 || datumKraja == null)){
                        status = "zelena"
                    }
                    // kviz nije uradjen i nije aktivan
                    else if(datumPocetka == 2 && datumKraja == 2){
                        status = "crvena"
                    }
                }
                else status = "plava"
                val pitanjeKvizViewModel = PitanjeAnketaViewModel()
                if(status == "plava")
               //     pitanjeKvizViewModel.getRezultatZaKviz(anketa, holder , onSuccess = ::onSuccess1, onError = ::onError)
                else{
                    //   val context: Context = holder.quizStatus.getContext()
                    //holder..text = ""
                   // val id: Int = context.getResources()
                     //   .getIdentifier(status, "drawable", context.getPackageName())
                    //holder.quizStatus.setImageResource(id)
                    return@withContext
                }
            }
        }
    }

    fun onSuccess1(rezultat: Int, holder: AnketaViewHolder) {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
              //  val context: Context = holder.quizStatus.getContext()
                if(rezultat != -1){
                 //   holder.progressBar.text = rezultat.toString()
                }
                else// holder.progressBar.text = ""
                //var id: Int = context.getResources()
                 //   .getIdentifier(status, "drawable", context.getPackageName())
                //holder.quizStatus.setImageResource(id)
                return@withContext
            }
        }
    }

    fun onError() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {

            }
        }
    }
}*/