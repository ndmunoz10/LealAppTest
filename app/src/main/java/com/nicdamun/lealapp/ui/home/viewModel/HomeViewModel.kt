package com.nicdamun.lealapp.ui.home.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.useCases.transactions.*
import com.nicdamun.lealapp.useCases.users.GetUserByIdUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val deleteAllTransactionsUseCase: DeleteAllTransactionsUseCase,
    private val getLocalTransactionsUseCase: GetLocalTransactionsUseCase,
    private val getTransactionsByTypeUseCase: GetTransactionsByTypeUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val loadTransactionsUseCase: LoadTransactionsUseCase,
    private val updateTransactionTypeUseCase: UpdateTransactionTypeUseCase,
    private val updateTransactionUserUseCase: UpdateTransactionUserUseCase
): ViewModel() {

    private val mainContentTransactionsObs: MutableLiveData<List<TransactionModel>> = MutableLiveData()
    private val sideBarTransactionObs: MutableLiveData<List<TransactionModel>> = MutableLiveData()

    fun deleteAllTransactions() {
        viewModelScope.launch {
            val transactionsWereDeleted = deleteAllTransactionsUseCase.invoke()
            if (transactionsWereDeleted) {
                sideBarTransactionObs.value = getLocalTransactionsUseCase.invoke()
                mainContentTransactionsObs.value = getTransactionsByTypeUseCase.invoke(TransactionType.Opened)
            }
        }
    }

    fun fetchTransactions() {
        viewModelScope.launch {
            sideBarTransactionObs.value = loadTransactionsUseCase.invoke()
            mainContentTransactionsObs.value = getTransactionsByTypeUseCase.invoke(TransactionType.Opened)
        }
    }

    fun mainContentTransactionsObs(): LiveData<List<TransactionModel>> = mainContentTransactionsObs

    fun sideBarTransactionObs(): LiveData<List<TransactionModel>> = sideBarTransactionObs

    fun updateTransaction(transaction: TransactionModel) {
        viewModelScope.launch {
            updateTransactionTypeUseCase.invoke(transaction.id, TransactionType.Opened)
            sideBarTransactionObs.value = loadTransactionsUseCase.invoke()
            val user = getUserByIdUseCase.invoke(transaction.userId)
            val transactionWasUpdated = updateTransactionUserUseCase.invoke(transaction.id, user)
            if (transactionWasUpdated) {
                mainContentTransactionsObs.value = getTransactionsByTypeUseCase.invoke(TransactionType.Opened)
            }
        }
    }

}