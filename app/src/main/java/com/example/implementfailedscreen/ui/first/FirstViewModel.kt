package com.example.implementfailedscreen.ui.first

import androidx.lifecycle.ViewModel
import com.example.implementfailedscreen.data.RepositoryImpl
import com.example.implementfailedscreen.domain.GetListUseCase
import com.example.implementfailedscreen.ui.common.UiStatus
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FirstViewModel(
    private val getListUseCase: GetListUseCase = GetListUseCase(RepositoryImpl())
) : ContainerHost<FirstState, FirstSideEffect>, ViewModel() {
    override val container: Container<FirstState, FirstSideEffect> = container<FirstState, FirstSideEffect>(
        FirstState()
    )

    init {
        intent {
            val item = getListUseCase.geItem()
            if (item != null) {
                reduce {
                    state.copy(
                        status = UiStatus.Success,
                        st = item.status,
                        digit = item.digit
                    )
                }
            }

        }
    }

    fun addServerMessage(message: String?) {
        intent {
            postSideEffect(FirstSideEffect.ShowServerMessage(message = message!!))
        }
    }

}