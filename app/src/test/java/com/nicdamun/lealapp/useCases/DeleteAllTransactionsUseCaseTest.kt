package com.nicdamun.lealapp.useCases

import com.nicdamun.lealapp.CoroutinesTestRule
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import com.nicdamun.lealapp.useCases.transactions.DeleteAllTransactionsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DeleteAllTransactionsUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()
    private val mockTransactionRepository = mock(TransactionRepository::class.java)
    private val deleteAllTransactionsUseCase = DeleteAllTransactionsUseCase(mockTransactionRepository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockTransactionRepository)
    }

    @Test
    fun `delete all transactions successfully`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionRepository.deleteAllTransactions()).thenReturn(true)
            val result = deleteAllTransactionsUseCase.invoke()
            verify(mockTransactionRepository).deleteAllTransactions()
            assertEquals(true, result)
        }
    }

    @Test
    fun `delete all transactions with failure`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionRepository.deleteAllTransactions()).thenReturn(false)
            val result = deleteAllTransactionsUseCase.invoke()
            verify(mockTransactionRepository).deleteAllTransactions()
            assertEquals(false, result)
        }
    }
}