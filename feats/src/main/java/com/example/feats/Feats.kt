package com.example.feats

object Feats {
    val all = listOf(
        Feat("Tough", "You have extra hit point in combat mode.",  3, 0, 0 ),
        Feat("Grapple", "You can force enemy to not move.",  4, 0, 0 ),

        Feat("Trivia", "You know more or less useful facts whenever you are.",  0, 3, 0 ),
        Feat("Good Memory", "You can recall an event from a past to analyze it for a future.",  0, 4, 0 ),

        Feat("Concentration", "You have extra chance to stay focused in combat mode.",  0, 0, 3 ),
        Feat("Interpersonal Impact", "You extra chance to impact other .",  0, 0, 4 ),

        Feat("Dash", "You can spend action points to move further.",  2, 2, 0 ),
        Feat("Sneak Attack", "You can prepare deadly strike.",  2, 3, 0 ),

        Feat("Iron Will", "You ignore part of taken dmg.",  2, 0, 3 ),
        Feat("Command Respect", "You command respect on battle field enemies has disadvantage attacking you.",  2, 0, 2),

        Feat("Taunt", "You know how to piss off enemy to force him to attack you.",  0, 2, 2 ),
        Feat("One Step Ahead", "Name enemy action and target if enemy will use it in this configuration he has disadvantage.",  2, 3, 0 ),
    )

}

data class Feat(
    val name: String,
    val description: String,
    val requiredBody: Int,
    val requiredMind: Int,
    val requiredSoul: Int
)