package ba.etf.rma22.projekat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.fragment.FragmentAnkete
import ba.etf.rma22.projekat.data.fragment.FragmentIstrazivanje
import ba.etf.rma22.projekat.data.fragment.FragmentPredaj
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private val grupaViewModel = GrupaViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        val fragments =
            mutableListOf(
                FragmentPredaj(),
               // FragmentAnkete(),
                FragmentIstrazivanje()
            //FragmentAnkete()
            )

        viewPager.offscreenPageLimit = 2
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,
            fragments.toMutableList(), lifecycle)
        viewPager.adapter = viewPagerAdapter


        AccountRepository.setContext(applicationContext)
        AnketaRepository.setContext(applicationContext)
        OdgovorRepository.setContext(applicationContext)
        PitanjeAnketaRepository.setContext(applicationContext)
        IstrazivanjeIGrupaRepository.setContext(applicationContext)
        TakeAnketaRepository.setContext(applicationContext)
        // NOVO
        val payload = intent?.getStringExtra("payload")
        if (payload != null) {
            grupaViewModel.promijeniHash(payload, onSuccess = ::onSuccess, onError = ::onError)
        }
        else grupaViewModel.promijeniHash("39590664-6275-430a-ab51-a784d4546f62", onSuccess = ::onSuccess, onError = ::onError)
        // NOVO

    }

    fun onSuccess(){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val toast = Toast.makeText(applicationContext, "Sve ok", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    fun onError() {
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val toast = Toast.makeText(applicationContext, "Neki error", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    fun refreshSecondFragmentText() {
        Handler(Looper.getMainLooper()).postDelayed({
           viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje())
                                                    //FragmentAnkete() )
        }, 5000)
    }

     fun zaustaviAnketu(){
        viewPager=findViewById(R.id.pager)
        Handler(Looper.getMainLooper()).postDelayed({
     viewPagerAdapter.refreshFragment(
                0,
               FragmentPredaj()
         //FragmentAnkete()
            )
        }, 50)

        Handler(Looper.getMainLooper()).postDelayed({
           viewPagerAdapter.refreshFragment(
                1,
                //FragmentIstrazivanje()
           FragmentPredaj())
                                           //         FragmentAnkete())
        }, 50)

      while(true){
            if(viewPagerAdapter.itemCount==2) break

           viewPagerAdapter.remove(2 )

        }

        //viewPager.offscreenPageLimit=2
        viewPager.adapter= viewPagerAdapter
        viewPager.currentItem=0

    }

    fun zavrsiAnketu(){
        viewPager=findViewById(R.id.pager)
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(
                0,
                FragmentPredaj()
                    //FragmentAnkete()
            )
        }, 50)
        var s : String
        s="Završili ste anketu "  + " u okviru istraživanja "+ "."
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(
                1,
              //  FragmentPoruka.newInstance(s)
           // FragmentPredaj())
                                                    FragmentAnkete())
        }, 50)

     /*   while(true){
            if(viewPagerAdapter.itemCount==2) break
            viewPagerAdapter.remove(2 )

        }*/

        viewPager.adapter= viewPagerAdapter
        viewPager.currentItem=1
        viewPager.offscreenPageLimit=2

    }

    fun brojOdgovorenih() {
        TODO("Not yet implemented")
    }

    companion object {
    //    private lateinit var viewPagerAdapter: ViewPagerAdapter
        fun newInstance(): MainActivity = MainActivity()
       lateinit var viewPagerAdapter: ViewPagerAdapter


    }
   inner class ViewPagerAdapter(
        fragmentManager: FragmentManager, var fragments: MutableList<Fragment>, lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return fragments.size
        }
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun add(index: Int, fragment: Fragment) {

            fragments.add(index, fragment)
            notifyItemChanged(index)
        }

        fun refreshFragment(index: Int, fragment: Fragment) {
            fragments[index] = fragment
            notifyItemChanged(index)
        }

        fun remove(index: Int) {
            fragments.removeAt(index)
            notifyItemChanged(index)
        }

        override fun getItemId(position: Int): Long {
            return fragments[position].hashCode().toLong()
        }

        override fun containsItem(itemId: Long): Boolean {
            return fragments.find { it.hashCode().toLong() == itemId } != null
        }
    }

}