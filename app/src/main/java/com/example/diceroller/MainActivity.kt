package com.example.diceroller

// Import des classes nécessaires pour créer une interface utilisateur avec Jetpack Compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

// Point d'entrée principal de l'application Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Définit le contenu de l'écran en utilisant Jetpack Compose
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

// Fonction composable qui affiche l'application
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center) // Centre le contenu à l'écran
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // Variable d'état qui stocke la valeur actuelle du dé (1 à 6)
    var result by remember { mutableIntStateOf((1..6).random()) }

    // En fonction du résultat, on sélectionne l'image correspondante du dé
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Disposition verticale des éléments : image du dé, espace, puis bouton
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Affiche l'image du dé selon la valeur
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            result = (1..6).random() // Nouveau chiffre aléatoire entre 1 et 6
        }) {
            Text(text = stringResource(R.string.roll))
        }
    }
}

// Permet de prévisualiser l'interface dans Android Studio sans lancer l'app
@Preview(showBackground = true)
@Composable
fun DiceRollerAppPreview() {
    DiceRollerApp()
}
