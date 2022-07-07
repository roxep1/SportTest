package com.bashkir.sporttest.data

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.withState
import com.bashkir.sporttest.data.models.Player
import com.bashkir.sporttest.data.models.Rivalry
import org.koin.androidx.compose.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named
import kotlin.random.Random

class SportViewModel(initialState: SportState) : MavericksViewModel<SportState>(initialState),
    KoinComponent {
    companion object {
        private const val MAX_COUNT = 3
    }

    fun getQuestion() = withState {
        val players =
            get<List<Player>>(named("Players")).minus(it.alreadyUsed.toSet())
        val selectedPlayer = players[(players.indices).random()]
        val selectedRivalry = get<List<Rivalry>>().find { rivalry ->
            rivalry.players.toList().contains(selectedPlayer)
        }
        val answer = (0 until 4).random()
        val options = arrayListOf<Player>()
        for (i in 0..3) {
            if (i != answer) {
                var isNotAdded = true
                while (isNotAdded) {
                    val player = players[Random.nextInt(players.size)]
                    if (!options.contains(player) && selectedPlayer != player) {
                        options.add(player)
                        isNotAdded = false
                    }
                }
            } else options.add(
                if (selectedRivalry!!.players.first == selectedPlayer) selectedRivalry.players.second
                else selectedRivalry.players.first
            )
        }
        setState {
            copy(
                alreadyUsed = alreadyUsed + selectedPlayer,
                count = count + 1,
                player = selectedPlayer,
                enemyOptions = options,
                rivalry = selectedRivalry
            )
        }
    }

    fun answer(player: Player) = setState { copy(answer = player) }

    fun startTest() {
        setState { copy(count = 0, alreadyUsed = listOf()) }
        getQuestion()
    }

    fun isEnough(): Boolean = withState(this) { it.count >= MAX_COUNT }
}

data class SportState(
    val alreadyUsed: List<Player> = listOf(),
    val count: Int = 0,
    val enemyOptions: List<Player> = listOf(),
    val rivalry: Rivalry? = null,
    val player: Player? = null,
    val answer: Player? = null
) : MavericksState