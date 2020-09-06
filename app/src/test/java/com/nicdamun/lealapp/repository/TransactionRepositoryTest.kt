package com.nicdamun.lealapp.repository

import android.accounts.NetworkErrorException
import com.nicdamun.lealapp.CoroutinesTestRule
import com.nicdamun.lealapp.database.entities.TransactionEntity
import com.nicdamun.lealapp.dtos.TransactionDTO
import com.nicdamun.lealapp.helpers.Result
import com.nicdamun.lealapp.models.TransactionModel
import com.nicdamun.lealapp.models.TransactionType
import com.nicdamun.lealapp.repository.transactions.TransactionLocalDataSource
import com.nicdamun.lealapp.repository.transactions.TransactionRemoteDataSource
import com.nicdamun.lealapp.repository.transactions.TransactionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
class TransactionRepositoryTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()
    private val mockTransactionRemoteDataSource = mock(TransactionRemoteDataSource::class.java)
    private val mockTransactionLocalDataSource = mock(TransactionLocalDataSource::class.java)
    private val transactionRepository = TransactionRepository(
        mockTransactionLocalDataSource,
        mockTransactionRemoteDataSource
    )
    private val transactionEntityList = listOf(
        TransactionEntity(
            id = 1,
            userId = 1,
            transactionType = TransactionType.Unopened,
            createdDate = "today",
            commerce = null,
            branch = null,
            user = null
        ),
        TransactionEntity(
            id = 2,
            userId = 2,
            transactionType = TransactionType.Unopened,
            createdDate = "yesterday",
            commerce = null,
            branch = null,
            user = null
        ),
        TransactionEntity(
            id = 3,
            userId = 3,
            transactionType = TransactionType.Unopened,
            createdDate = "on friday",
            commerce = null,
            branch = null,
            user = null
        )
    )

    private val transactionDTOList = listOf(
        TransactionDTO(
            id = 1,
            userId = 1,
            createdDate = "today",
            commerce = null,
            branch = null
        ),
        TransactionDTO(
            id = 2,
            userId = 2,
            createdDate = "yesterday",
            commerce = null,
            branch = null
        ),
        TransactionDTO(
            id = 3,
            userId = 3,
            createdDate = "on friday",
            commerce = null,
            branch = null
        )
    )

    private val transactionModelList = listOf(
        TransactionModel(
            id = 1,
            userId = 1,
            transactionType = TransactionType.Unopened,
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
            transactionType = TransactionType.Unopened,
            createdDate = "on friday",
            commerce = null,
            branchModel = null,
            user = null
        )
    )

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockTransactionLocalDataSource, mockTransactionRemoteDataSource)
    }

    @Test
    fun `delete all transactions successful`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionLocalDataSource.deleteAll()).thenReturn(true)
            val result = transactionRepository.deleteAllTransactions()
            verify(mockTransactionLocalDataSource).deleteAll()
            assertEquals(true, result)
        }
    }

    @Test
    fun `delete all transactions failure`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionLocalDataSource.deleteAll()).thenReturn(false)
            val result = transactionRepository.deleteAllTransactions()
            verify(mockTransactionLocalDataSource).deleteAll()
            assertEquals(false, result)
        }
    }

    @Test
    fun `fetch transactions when local database is not empty`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionLocalDataSource.getTransactions()).thenReturn(
                transactionEntityList
            )
            val result = transactionRepository.fetchTransactions()
            verify(mockTransactionLocalDataSource).getTransactions()
            assertEquals(transactionModelList, result)
        }
    }

    @Test
    fun `fetch successfully service transactions when local database is empty`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionLocalDataSource.getTransactions()).thenReturn(emptyList(), transactionEntityList)
            `when`(mockTransactionRemoteDataSource.getTransactions()).thenReturn(Result.Success(transactionDTOList))
            `when`(mockTransactionLocalDataSource.saveTransactions(transactionEntityList)).thenReturn(true)
            val result = transactionRepository.fetchTransactions()
            verify(mockTransactionLocalDataSource, times(2)).getTransactions()
            verify(mockTransactionLocalDataSource).saveTransactions(transactionEntityList)
            verify(mockTransactionRemoteDataSource).getTransactions()
            assertEquals(transactionModelList, result)
        }
    }

    @Test
    fun `fetch failure service transactions when local database is empty`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(mockTransactionLocalDataSource.getTransactions()).thenReturn(emptyList(), transactionEntityList)
            `when`(mockTransactionRemoteDataSource.getTransactions()).thenReturn(Result.Failure(NetworkErrorException()))
            val result = transactionRepository.fetchTransactions()
            verify(mockTransactionLocalDataSource).getTransactions()
            verify(mockTransactionRemoteDataSource).getTransactions()
            assertEquals(emptyList<TransactionModel>(), result)
        }
    }
}