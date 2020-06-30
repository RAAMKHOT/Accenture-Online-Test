package com.accenture.onlinetest.screens.landing

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.accenture.onlinetest.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.accenture.onlinetest.helper.CommonUtils
import com.accenture.onlinetest.models.Base
import com.accenture.onlinetest.models.Rows
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity(), LandingView {
    private lateinit var factListAdapter: FactsListAdapter
    private lateinit var shimmerContainer: ShimmerFrameLayout
    private var landingPresenter: LandingPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        init()
        checkNetworkStatus()
    }

    override fun init() {
        shimmerContainer = shimmer_view_container as ShimmerFrameLayout
        shimmerContainer.startShimmerAnimation()
        landingPresenter = LandingPresenter(this)
        factListAdapter = FactsListAdapter(ArrayList<Rows>(), this)
        val mLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
        factsRecyclerView.layoutManager = mLayoutManager
        factsRecyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        factsRecyclerView.adapter = factListAdapter

        swipeToRefresh.setOnRefreshListener {
            landingPresenter!!.getFactData()
        }

        setTitle("")
        landingPresenter!!.getFactData()
    }

    private fun initListView(factList: ArrayList<Rows>) {
        factListAdapter.setList(factList)
        shimmerContainer.stopShimmerAnimation()
        if (swipeToRefresh.isRefreshing) {
            swipeToRefresh.isRefreshing = false
            CommonUtils.showToast(this, "Refreshed")
        }
    }

    override fun onSuccess(factList: Base) {
        setTitle(factList.title)
        initListView(factList.rows as ArrayList<Rows>)
    }

    override fun onError(error: String) {
        CommonUtils.showToast(this, error)

        shimmerContainer.stopShimmerAnimation()
        if (swipeToRefresh.isRefreshing) {
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun checkNetworkStatus(){
        if(CommonUtils.isNetworkAvailable(this)){
            textViewNoInternetConnection.visibility= View.GONE
            shimmer_view_container.visibility= View.VISIBLE
        }else{
            textViewNoInternetConnection.visibility= View.VISIBLE
            shimmer_view_container.visibility= View.GONE

        }
    }
}
