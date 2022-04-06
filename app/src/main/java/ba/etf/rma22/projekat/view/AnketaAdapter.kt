package ba.etf.rma22.projekat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.Anketa
import java.text.SimpleDateFormat
import java.util.*

class AnketaAdapter(
    private var ankete: List<Anketa>
)  : RecyclerView.Adapter<AnketaAdapter.AnketaViewHolder>(){
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_anketa, parent, false)
        return AnketaViewHolder(view)
    }

    override fun getItemCount(): Int = ankete.size

    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        holder.nazivAnkete.text = ankete[position].naziv
        holder.istrazivanje.text = ankete[position].nazivIstrazivanja
        val formatDatuma = SimpleDateFormat("dd.MM.yyyy")
        var pr1: Float = ankete[position].progres * 100
        pr1= Math.round(pr1).toFloat()
        var pr: Int = pr1.toInt()
        if(pr%2!=0){
            pr += 10
        }

        holder.progressBar.setProgress(pr)
        if (ankete[position].datumRada!=null /*&& ankete[position].datumKraj.after(ankete[position].datumRada)*/) {
            holder.txtViewZaDatum.text = "Anketa urađena: " + formatDatuma.format(ankete[position].datumRada)
            holder.anketaImage.setImageResource(R.drawable.plava)
        } else if (ankete[position].datumRada == null && ankete[position].datumPocetak.before(
                Calendar.getInstance().time) && ankete[position].datumKraj.after(
                Calendar.getInstance().time)
        ) {
            holder.txtViewZaDatum.text =
                "Vrijeme zatvaranja: " + formatDatuma.format(ankete[position].datumKraj)
            holder.anketaImage.setImageResource(R.drawable.zelena)

        } else if (ankete[position].datumPocetak.after(Calendar.getInstance().time)) {
            holder.txtViewZaDatum.text =
                "Vrijeme aktiviranja: " + formatDatuma.format(ankete[position].datumPocetak)
            holder.anketaImage.setImageResource(R.drawable.zuta)
        }
        else if(ankete[position].datumRada == null && ankete[position].datumKraj.before(Calendar.getInstance().time)){
            holder.txtViewZaDatum.text =
                "Anketa zatvorena: " + formatDatuma.format(ankete[position].datumKraj)
            holder.anketaImage.setImageResource(R.drawable.crvena)

        }
    }

    fun updateAnkete(ankete1: List<Anketa>) {
        ankete = ankete1
        notifyDataSetChanged()
    }

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val anketaImage: ImageView = itemView.findViewById(R.id.imageView)
        val txtViewZaDatum: TextView = itemView.findViewById(R.id.datum)
        val nazivAnkete: TextView =itemView.findViewById(R.id.nazivAnkete)
        val istrazivanje: TextView =itemView.findViewById(R.id.brojIstrazivanja)
        val progressBar: ProgressBar =itemView.findViewById(R.id.progresZavrsetka)
    }

}