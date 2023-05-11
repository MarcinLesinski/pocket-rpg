package com.example.pocket_rpg.presentation

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocket_rpg.characters.view.character_level_up.CharacterLevelUpScreen
import com.example.pocket_rpg.characters.view.character_list.CharacterListScreen
import com.example.pocket_rpg.characters.view.character_preview.CharacterDetailScreen
import com.example.pocket_rpg.game.view.create_game.CreateGameScreen
import com.example.pocket_rpg._common.main_menu.MainMenuScreen
import com.example.pocket_rpg.account.SignInScreen
import com.example.pocket_rpg.characters.view.create_new_character.NewCharacterScreen
import com.example.pocket_rpg.game_master.view.master_screen.MasterScreen
import com.example.pocket_rpg.player.view.player_screen.PlayerScreen
import com.example.pocket_rpg.tests_screen.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.MainMenu()
    ) {
        composable(Route.MainMenu()) { MainMenuScreen(navController) }
        composable(Route.Characters()) { CharacterListScreen(navController) }
        composable(Route.CharacterPreview()) { CharacterDetailScreen(navController) }
        composable(Route.CharacterLevelUp()) { CharacterLevelUpScreen(navController) }
        composable(Route.CharacterCreateNew()){ NewCharacterScreen(navController)}
        composable(Route.CreateGame()){ CreateGameScreen(navController) }
        composable(Route.MasterScreen()){ MasterScreen() }
        composable(Route.PlayerScreen()){ PlayerScreen() }
//        composable(Route.SignInScreen()){ SignInScreen() }
        composable("test"){ Screen() }
    }
}