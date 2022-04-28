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
import ba.etf.rma22.projekat.data.fragment.FragmentAnkete.Companion.indeks
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



    companion object {
        fun newInstance(pitanje : Pitanje): FragmentPitanje = FragmentPitanje(pitanje)

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
        tekstPitanja.setText(pitanje.tekst)

        adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, pitanje.opcije)
        ponudjeniOdgovori.adapter = adapter
     //   odgovoriAD= MyAdapter(view.context, odgovori, pitanje.odgovor)
       // odgovori.addAll(pitanje.opcije)
        //odgovoriAD= MyAdapter(view.context, odgovori, pitanje.odgovor)

        var i =0
        if(indeks!=-1){
            for(pO in ponudjeniOdgovori) {
                if(i==indeks)
                    (ponudjeniOdgovori.getChildAt(indeks) as TextView).setTextColor(Color.parseColor("#0000FF"))
                else
                    (ponudjeniOdgovori.getChildAt(i) as TextView).setTextColor(Color.parseColor("#000000"))
                i++
            }
        }


      ponudjeniOdgovori.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                FragmentPredaj().uvecajProgres(FragmentAnkete.velicina)
                var i =0
                indeks=p2
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

/*class MyAdapter(private val context : Context, private val arrayList : java.util.ArrayList<String>, vecUradjene : String)
    : ArrayAdapter<String>(context, layoutResource, elements){
    private lateinit var odgovor : TextView
    private var mContext = context
    private var vecUradjene=vecUradjene

    var mcolor = Color.BLUE


    override fun getView(position : Int, convertView : View?, parent : ViewGroup): View? {
        var convertView=convertView
        convertView=LayoutInflater.from(context).inflate(R.layout.item_odgovor, parent, false)
        odgovor=convertView.findViewById((R.id.idTextViewElementa))
        odgovor.text=arrayList[position]

        convertView.setOnClickListener (View.OnClickListener{
            convertView.isSelected = true
            val selected=(convertView.findViewById(R.id.idTextViewElementa) as TextView).text.toString()
            ListaOdgovora.odgovori.add(selected)

            if(mContext is MainActivity){
                (mContext as MainActivity).brojOdgovorenih()
            }
        })

        for(odg in ListaOdgovora.odgovori){
            if(odgovor.getText().equals(odg)){
                odgovor.setTextColor(mcolor)
            }
        }
        if(odgovor.getText().equals(vecUradjene)){
            odgovor.setTextColor(mcolor)
        }
        return convertView

    }

}*/

