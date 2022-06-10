package ba.etf.rma22.projekat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.fragment.FragmentAnkete
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel


//private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        val fragments =
            mutableListOf(
                FragmentAnkete(),
                //FragmentIstrazivanje()
            FragmentAnkete()
            )

        viewPager.offscreenPageLimit = 2
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,
            fragments.toMutableList(), lifecycle)
        viewPager.adapter = viewPagerAdapter


    }

    fun refreshSecondFragmentText() {
        Handler(Looper.getMainLooper()).postDelayed({
           viewPagerAdapter.refreshFragment(1, //FragmentIstrazivanje()
                                                    FragmentAnkete() )
        }, 5000)
    }

     fun zaustaviAnketu(){
        viewPager=findViewById(R.id.pager)
        Handler(Looper.getMainLooper()).postDelayed({
     viewPagerAdapter.refreshFragment(
                0,
                FragmentAnkete()
            )
        }, 50)

        Handler(Looper.getMainLooper()).postDelayed({
           viewPagerAdapter.refreshFragment(
                1,
                //FragmentIstrazivanje()
            FragmentAnkete())
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
                FragmentAnkete()
            )
        }, 50)
        var s : String
        s="Završili ste anketu "  + " u okviru istraživanja "+ "."
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(
                1,
              //  FragmentPoruka.newInstance(s)
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