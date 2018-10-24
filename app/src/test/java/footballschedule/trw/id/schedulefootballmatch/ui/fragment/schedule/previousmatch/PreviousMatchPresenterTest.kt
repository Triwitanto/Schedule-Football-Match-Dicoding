package footballschedule.trw.id.schedulefootballmatch.ui.fragment.schedule.previousmatch

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

class PreviousMatchPresenterTest{

    @Mock
    private
    lateinit var view: PreviousMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PreviousMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PreviousMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val events: MutableList<Event> = mutableListOf()
        val response = MatchEvent(events)
        val eventPrevious = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(ApiUrl.getPreviousMatch(eventPrevious)),
                MatchEvent::class.java
        )).thenReturn(response)

        presenter.getPreviousMatch(eventPrevious)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showPriviousMatchList(events)
        Mockito.verify(view).hideLoading()
    }
}