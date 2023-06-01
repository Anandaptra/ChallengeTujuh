package com.example.challengeenam.viewmodel


import com.example.challengeenam.model.ResponseMovie
import com.example.challengeenam.network.ResultApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call

class MovieViewModelTest {
    @Mock
    lateinit var resultApi : ResultApi
    private lateinit var viewModel: MovieViewModel

//    lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        resultApi = mockk()
        viewModel = MovieViewModel(resultApi)
    }

    @Test
    fun testRetriveData(): Unit = runBlocking {
        //membuat objek palsu (mock) responseRetrive dari kelas Call<List<Source>>
        //Objek palsu ini akan digunakan sebagai respons palsu dari pemanggilan service.getAllSources().
        val responseRetrive = mockk<Call<ResponseMovie>>()

        every {
            runBlocking {
                resultApi.getTrendingMovie("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI1IiwiZXhwIjoxNjk5MDg5NTI4LCJpYXQiOjE2Njc1NTM1MjgsImlzcyI6ImFtaW5pdmFuIn0.A1srn810rwLwLeoaUl1zJaoTcy5noFB8Gs10hY_cGDc")
            }
        } returns responseRetrive
        val result = resultApi.getTrendingMovie("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI1IiwiZXhwIjoxNjk5MDg5NTI4LCJpYXQiOjE2Njc1NTM1MjgsImlzcyI6ImFtaW5pdmFuIn0.A1srn810rwLwLeoaUl1zJaoTcy5noFB8Gs10hY_cGDc")

        //verify, kita memastikan bahwa metode service.getAllSources() benar-benar dipanggil dengan argumen yang sesuai.

        verify {
            runBlocking {
                resultApi.getTrendingMovie("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI1IiwiZXhwIjoxNjk5MDg5NTI4LCJpYXQiOjE2Njc1NTM1MjgsImlzcyI6ImFtaW5pdmFuIn0.A1srn810rwLwLeoaUl1zJaoTcy5noFB8Gs10hY_cGDc")
            }
        }

        //assertEquals, kita membandingkan nilai result yang diperoleh dari pemanggilan service.getAllSources()
        // dengan objek palsu responseRetrive, untuk memastikan bahwa hasilnya sesuai dengan yang diharapkan.
        assertEquals(result,responseRetrive)

    }

}