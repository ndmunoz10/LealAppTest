package com.nicdamun.lealapp.useCases

import com.nicdamun.lealapp.CoroutinesTestRule
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import com.nicdamun.lealapp.useCases.transactions.GetLocalTransactionsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class GetLocalTransactionsUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()
    private val mockTransactionRepository = mock(TransactionRepository::class.java)
    private val getLocalTransactionsUseCase = GetLocalTransactionsUseCase(mockTransactionRepository)

    private val transactionModelList = listOf(
        TransactionModel(
            id = 1,
            userId = 1,
            transactionType = TransactionType.Opened,
            createdDate = "today",
            commerce = null,
            branchModel = null,
            user = null
        ),
        TransactionModel(
            id = 2,
            userId = 2,
            transactionType = TransactionType.Unopened,
            createdDate = "yesterday",
            commerce = null,
            branchModel = null,
            user = null
        ),
        TransactionModel(
            id = 3,
            userId = 3,
            transactionType = TransactionType.Opened,
            createdDate = "on friday",
            commerce = null,
            branchModel = null,
            user = null
        )
    )

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockTransactionRepository)
    }

    @Test
    fun `get local transactions successful`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionRepository.getLocalTransactions()).thenReturn(transactionModelList)
            val result = getLocalTransactionsUseCase.invoke()
            verify(mockTransactionRepository).getLocalTransactions()
            assertEquals(transactionModelList, result)
        }
    }
}