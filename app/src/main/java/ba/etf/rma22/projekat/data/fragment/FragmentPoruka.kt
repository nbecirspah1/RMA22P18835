package ba.etf.rma22.projekat.data.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R

class FragmentPoruka : Fragment(){
    private lateinit var poruka: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_poruka, container, false)
        poruka=view.findViewById(R.id.tvPoruka)
       poruka.setText(arguments?.getString("tekstZaIspis"))
        return view
    }


    companion object {
        fun newInstance(tekstZaIspis: String): FragmentPoruka = FragmentPoruka().apply {
            arguments = Bundle().apply {
                putString("tekstZaIspis", tekstZaIspis)
            }
        }
    }
}
