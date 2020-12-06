package com.gxp.ddup_douban.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.gxp.ddup_douban.R
import com.gxp.ddup_douban.ui.fragment.HomeFragment
import com.gxp.ddup_douban.ui.fragment.MineFragment
import com.gxp.ddup_douban.ui.fragment.MovieFragment
import com.gxp.ddup_douban.ui.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var homeFragment: HomeFragment? = null
    private var mineFragment: MineFragment? = null
    private var movieFragment: MovieFragment? = null
    private var searchFragment: SearchFragment? = null


    private var mFragments: Array<Fragment> = arrayOf(
        HomeFragment.newInstance(),
        SearchFragment.newInstance(),
        MovieFragment.newInstance(),
        MineFragment.newInstance()
    )

    val mTabRes = intArrayOf(
        R.drawable.tab_home_normal,
        R.drawable.tab_search_normal,
        R.drawable.tab_movie_normal,
        R.drawable.tab_mine_normal
    )
    val mTabResPressed = intArrayOf(
        R.drawable.tab_home_selected,
        R.drawable.tab_search_selected,
        R.drawable.tab_movie_selected,
        R.drawable.tab_mine_selected
    )
    private val mTabTitle = arrayOf("首页", "搜索", "电影", "我的")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


    }

    private fun initView() {

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                onTabItemSelected(tab!!.position)
                for (i in 0 until tab_layout.getTabCount()) {
                    val view: View? = tab_layout.getTabAt(i)?.customView
                    val icon = view?.findViewById<View>(R.id.tab_content_image) as ImageView
                    val text = view.findViewById<View>(R.id.tab_content_text) as TextView
                    if (i == tab.position) {
                        icon.setImageResource((mTabResPressed[i]))
                        text.setTextColor(resources.getColor(android.R.color.black))

                    } else {
                        icon.setImageResource((mTabRes[i]))
                        text.setTextColor(resources.getColor(android.R.color.darker_gray))
                    }
                }

            }

        })

        for (i in 0 until 4) {
            tab_layout.addTab(tab_layout.newTab().setCustomView(getTabView(i)))
        }
    }

    private fun getTabView(i: Int): View {
        val view: View =
            LayoutInflater.from(this).inflate(R.layout.home_tab_content, null)
        val iv = view.findViewById<View>(R.id.tab_content_image) as ImageView
        val tv = view.findViewById<View>(R.id.tab_content_text) as TextView
        iv.setImageResource(mTabRes[i])
        tv.setText(mTabTitle[i])
        return view
    }

    private fun onTabItemSelected(position: Int) {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = mFragments[0]
            1 -> fragment = mFragments[1]
            2 -> fragment = mFragments[2]
            3 -> fragment = mFragments[3]
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_content, fragment)
                .commit()
        }
    }
}