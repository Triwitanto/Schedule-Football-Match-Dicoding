package footballschedule.trw.id.schedulefootballmatch.util

import kotlin.coroutines.experimental.CoroutineContext
import kotlinx.coroutines.experimental.android.UI

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}
