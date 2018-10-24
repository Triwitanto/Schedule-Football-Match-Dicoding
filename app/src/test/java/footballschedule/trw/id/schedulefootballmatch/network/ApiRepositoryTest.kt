package footballschedule.trw.id.schedulefootballmatch.network

import org.junit.Test
import org.mockito.Mockito

class ApiRepositoryTest {
    @Test
    fun testDoRequest() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id=4346"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
}