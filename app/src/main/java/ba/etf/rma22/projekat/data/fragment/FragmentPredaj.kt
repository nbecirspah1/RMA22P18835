package ba.etf.rma22.projekat.data.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel


class FragmentPredaj : Fragment() {
    private lateinit var tekst: TextView
    private lateinit var dugme: Button
    private lateinit var anketaViewModel: AnketaViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_predaj, container, false)
        tekst = view.findViewById(R.id.progresTekst)
        dugme = view.findViewById(R.id.dugmePredaj)
        tekst.text=progres.toString()+'%'
      //  anketaViewModel.setProgress(progres/100.toFloat())

        dugme.setOnClickListener {

            (activity as MainActivity).zavrsiAnketu()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        tekst.text=progres.toString()+'%'

    }



    companion object {
        var progres : Int
        init{progres=0}
        var progres1 : Double
        init{progres1=0.0}
        fun newInstance(): FragmentPredaj = FragmentPredaj()
    }

    fun uvecajProgres(velicina : Int){
        progres1 += 1 / (velicina.toDouble())
        var pr =(progres1*100).toInt()
        var jedinice = pr%10
        pr=pr/10
        var desetice = pr%10
        if(desetice%2!=0 && jedinice>=5){ progres=(desetice+1)*10}
        else if(desetice%2!=0 && jedinice<5){progres=(desetice-1)*10}
        else if (desetice==0){progres=100}
        else if(desetice%2==0){progres=desetice*10}

    }
}
