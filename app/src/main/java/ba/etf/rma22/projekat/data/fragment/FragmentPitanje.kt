package ba.etf.rma22.projekat.data.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R

import ba.etf.rma22.projekat.data.fragment.FragmentPitanje.Companion.indeks1
import ba.etf.rma22.projekat.data.models.ListaOdgovora
import ba.etf.rma22.projekat.data.models.Pitanje


class FragmentPitanje(private val pitanje : Pitanje): Fragment() {

    private lateinit var tekstPitanja: TextView
    private lateinit var ponudjeniOdgovori: ListView
    private lateinit var dugmeZaustavi: Button
    private lateinit var adapter: ArrayAdapter<String>
    //  private lateinit var odgovoriAD : MyAdapter
    private lateinit var view1 : View
    var odgovori= arrayListOf<String>()
    private lateinit var opcije: ArrayList<String>





    companion object {
        fun newInstance(pitanje : Pitanje): FragmentPitanje = FragmentPitanje(pitanje)
        var indeks1=-1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_pitanje, container, false)
        tekstPitanja = view.findViewById(R.id.tekstPitanja)
        ponudjeniOdgovori = view.findViewById(R.id.odgovoriLista)
        dugmeZaustavi = view.findViewById(R.id.dugmeZaustavi)
        view1=view
        //tekstPitanja.setText(pitanje.tekst)

        for (opcija in pitanje.opcije){
            opcije.add(opcija.toString())
        }
        adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, opcije)
        ponudjeniOdgovori.adapter = adapter
        //   odgovoriAD= MyAdapter(view.context, odgovori, pitanje.odgovor)
        // odgovori.addAll(pitanje.opcije)
        //odgovoriAD= MyAdapter(view.context, odgovori, pitanje.odgovor)

        var i =0


        ponudjeniOdgovori.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               // FragmentPredaj().uvecajProgres(FragmentAnkete.velicina)
                var i =0
                indeks1=p2
                for(pO in ponudjeniOdgovori) {
                    if(i==p2)
                        (ponudjeniOdgovori.getChildAt(p2) as TextView).setTextColor(Color.parseColor("#0000FF"))
                    else
                        (ponudjeniOdgovori.getChildAt(i) as TextView).setTextColor(Color.parseColor("#000000"))
                    i++
                }

            }
        }

        dugmeZaustavi.setOnClickListener {
            (activity as MainActivity).zaustaviAnketu()
        }

        return view
    }


}
/*
class MyAdapter( context : Context, private val layoutResource : Int, private val elements : List<String>)
    : ArrayAdapter<String>(context, layoutResource, elements){
    override fun getView(position : Int, convertView : View?, parent : ViewGroup) : View{
        val view=super.getView(position, convertView, parent)
        val textView=view.findViewById<TextView>(android.R.id.text1)

         var i =0
        if(indeks1!=-1){

                if(position==indeks1)
                    textView.setTextColor(Color.parseColor("#0000FF"))
                else
                    textView.setTextColor(Color.parseColor("#000000"))

        }

return view
    }*/
//}

