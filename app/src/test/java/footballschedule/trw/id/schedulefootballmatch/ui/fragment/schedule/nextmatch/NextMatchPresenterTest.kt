package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.nextmatch

import com.google.gson.Gson
import footballschedule.trw.id.schedulefootballmatch.TestContextProvider
import footballschedule.trw.id.schedulefootballmatch.network.ApiRepository
import footballschedule.trw.id.schedulefootballmatch.network.ApiUrl
import footballschedule.trw.id.schedulefootballmatch.network.model.Event
import footballschedule.trw.id.schedulefootballmatch.network.model.MatchEvent
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest{
    @Mock
    private
    lateinit var view: NextMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val events: MutableList<Event> = mutableListOf()
        val response = MatchEvent(events)
        val eventNext = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(ApiUrl.getNextMatch(eventNext)),
                MatchEvent::class.java
        )).thenReturn(response)

        presenter.getNextMatch(eventNext)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showNextMatchList(events)
        Mockito.verify(view).hideLoading()
    }
}