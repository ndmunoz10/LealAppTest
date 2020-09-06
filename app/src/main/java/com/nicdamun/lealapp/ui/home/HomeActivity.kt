package com.nicdamun.lealapp.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nicdamun.lealapp.R
import com.nicdamun.lealapp.common.BaseActivity
import com.nicdamun.lealapp.databinding.ActivityHomeBinding
import com.nicdamun.lealapp.extensions.rotate
import com.nicdamun.lealapp.extensions.showIn
import com.nicdamun.lealapp.extensions.showOut
import com.nicdamun.lealapp.helpers.SwipeToDeleteCallback
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.ui.home.adapters.MainContentAdapter
import com.nicdamun.lealapp.ui.home.adapters.SideNavAdapter
import com.nicdamun.lealapp.ui.home.viewModel.HomeViewModel

class HomeActivity : BaseActivity(), TransactionInteraction {

    private var isFabRotated = false
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mainContentAdapter: MainContentAdapter
    private lateinit var sideNavAdapter: SideNavAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setupObservers()
        viewModel.fetchTransactions()
    }

    private fun handleFABClick() {
        binding.run {
            isFabRotated = fabHomeMainButton.rotate(!isFabRotated)
            if (isFabRotated) {
                fabHomeDeleteTransactions.showIn()
                fabHomeRefreshTransactions.showIn()
            } else {
                fabHomeDeleteTransactions.showOut()
                fabHomeRefreshTransactions.showOut()
            }
        }
    }

    private fun handleRetrievedMainContentTransactions(transactions: List<TransactionModel>?) {
        mainContentAdapter.submitList(transactions)
    }

    private fun handleRetrievedSideBarTransactions(transactions: List<TransactionModel>?) {
        sideNavAdapter.submitList(transactions)
    }

    private fun initViews() {
        initializeRecyclerView()
        setupDrawer()
        setupClickListeners()
    }

    private fun initializeRecyclerView() {
        sideNavAdapter = SideNavAdapter(this)
        mainContentAdapter = MainContentAdapter { position, transactionModel -> showUndoSnackBar(position, transactionModel) }
        binding.run {
            includeFragmentSideNav.rvSideNavTransactions.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@HomeActivity.sideNavAdapter
            }
            rvTransactionsDetail.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@HomeActivity.mainContentAdapter
                val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(this@HomeActivity.mainContentAdapter, context))
                itemTouchHelper.attachToRecyclerView(this)
            }
        }
    }

    private fun setupDrawer() {
        binding.run {
            val toggle = ActionBarDrawerToggle(this@HomeActivity, drawerLayout, tbHomeToolbar, R.string.commerce_label, R.string.leal_motto)
            drawerLayout.addDrawerListener(toggle)
            toggle.isDrawerIndicatorEnabled = true
            toggle.syncState()
        }
    }

    private fun setupClickListeners() {
        binding.run {
            fabHomeMainButton.setOnClickListener { handleFABClick() }
            fabHomeDeleteTransactions.setOnClickListener { viewModel.deleteAllTransactions() }
            fabHomeRefreshTransactions.setOnClickListener { viewModel.fetchTransactions() }
        }
    }

    private fun showUndoSnackBar(position: Int, transactionModelDeleted: TransactionModel) {
        val view = binding.root
        val snackBar = Snackbar.make(view, "Transaction Deleted!", Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo") { undoDelete(position, transactionModelDeleted) }
        snackBar.show()
    }

    private fun undoDelete(position: Int, transactionModelDeleted: TransactionModel) {
        val newList = mainContentAdapter.currentList.toMutableList()
        newList.add(position, transactionModelDeleted)
        mainContentAdapter.submitList(newList)
    }

    private fun setupObservers() {
        viewModel.mainContentTransactionsObs().observe(this, { transactions -> handleRetrievedMainContentTransactions(transactions) })
        viewModel.sideBarTransactionObs().observe(this, { transactions -> handleRetrievedSideBarTransactions(transactions) })
    }

    override fun onTransactionClicked(transactionModel: TransactionModel) {
        Toast.makeText(this, transactionModel.createdDate, Toast.LENGTH_SHORT).show()
        viewModel.updateTransaction(transactionModel)
    }
}

interface TransactionInteraction {
    fun onTransactionClicked(transactionModel: TransactionModel)
}