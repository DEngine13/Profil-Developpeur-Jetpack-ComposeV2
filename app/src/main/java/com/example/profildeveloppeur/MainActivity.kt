package com.example.profildeveloppeur

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

// Le typage de la donnée pour Projet
data class Projet(
    val id: Int,
    val titre: String,
    val description: String
)

// Main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfilDeveloppeurTheme {
                ProfilDevelopeurEcran(
                    nom = "Beff Jezos",
                    metier = "Développeur C++",
                    specialite = "Spécialiste Gameplay",
                    email = "beff.jezos@example.com",
                    disponible = true,
                    description = "Développeur de jeu vidéo spécialisé dans le gameplay avec quatre années d'expérience.",
                    competences = listOf("C++", "Unreal Engine", "Git", "Kotlin"),
                    projets = listOf(
                        Projet(id = 1, titre = "Dungeon!", description = "Un jeu d'action dans un donjon généré procéduralement sous Unreal Engine."),
                        Projet(id = 2, titre = "Turbo Kart", description = "Une réinterprétation de Mario Kart sous Unity.")
                    )
                )
            }
        }
    }
}

// Écran développeur
@Composable
fun ProfilDevelopeurEcran(
    nom: String,
    metier: String,
    specialite: String,
    email: String,
    disponible: Boolean,
    description: String,
    competences: List<String>,
    projets: List<Projet>,
    modifier: Modifier = Modifier
) {

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ImageDeProfil(disponible = disponible)
                        Spacer(modifier = Modifier.width(16.dp))
                        Titre(nom = nom, metier = metier)
                    }
                }

                item {
                    Specialite(nom = specialite)
                }

                item {
                    Text(text = description)
                }

                item {
                    Column {
                        Text(text = "Compétences", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        ListeCompetences(competences = competences)
                    }
                }

                item {
                    Text(text = "Projets", style = MaterialTheme.typography.titleMedium)
                }

                items(
                    items = projets,
                    key = { projet -> projet.id }
                ) { projet ->
                    CardProjet(projet = projet)
                }

                item {
                    ChampEmail(email = email, modifier = Modifier.fillMaxWidth())
                }

                item {
                    BoutonContact(
                        onClick = { Log.d("Profil", "Bonjour, je suis $nom") }
                    )
                }
            }
        }
    }
}

// Titre avec le nom, prénom et le métier
@Composable
fun Titre(nom: String, metier: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = nom,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = metier)
    }
}

// Image de profil du développeur et s'il est disponible
@Composable
fun ImageDeProfil(disponible: Boolean, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Image de profil du développeur",
            modifier = Modifier.size(96.dp)
        )
        Surface(
            modifier = Modifier.align(Alignment.BottomEnd),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Disponibilite(
                disponible = disponible,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

// Spécialité du développeur
@Composable
fun Specialite(nom: String, modifier: Modifier = Modifier) {
    Text(text = "$nom", modifier = modifier)
}

// Fonction pour indiquer si le développeur est disponible ou non
@Composable
fun Disponibilite(disponible: Boolean, modifier: Modifier = Modifier) {
    val texte = if (disponible) "Disponible" else "Indisponible"
    Text(
        text = texte,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}

// LazyRow pour ranger les compétences de façon horizontale
@Composable
fun ListeCompetences(competences: List<String>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(competences) { competence ->
            Competence(label = competence)
        }
    }
}

// Badge pour les compétences
@Composable
fun Competence(label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// La carte pour les projets
@Composable
fun CardProjet(projet: Projet, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = projet.titre, style = MaterialTheme.typography.titleMedium)
            Text(text = projet.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Le champ pour l'email
@Composable
fun ChampEmail(email: String, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = email,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(text = "Email")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
        },
        modifier = modifier
    )
}

// Le bouton de contact
@Composable
fun BoutonContact(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = "Me contacter")
    }
}

// Preview 1
@Preview(showBackground = true, name = "Jeu vidéo - Light Theme")
@Composable
fun ApercuJeuVideo() {
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfilDevelopeurEcran(
            nom = "Beff Jezos",
            metier = "Développeur C++",
            specialite = "Spécialiste Gameplay",
            email = "beff.jezos@example.com",
            disponible = true,
            description = "Développeur de jeu vidéo spécialisé dans le gameplay avec quatre années d'expérience.",
            competences = listOf("C++", "Unreal Engine", "Git", "Kotlin"),
            projets = listOf(
                Projet(id = 1, titre = "Dungeon!", description = "Un jeu d'action dans un donjon généré procéduralement sous Unreal Engine."),
                Projet(id = 2, titre = "Turbo Kart", description = "Une réinterprétation de Mario Kart sous Unity.")
            )
        )
    }
}

// Preview 2
@Preview(showBackground = true, name = "Go - Dark Theme")
@Composable
fun ApercuGo() {
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfilDevelopeurEcran(
            nom = "Melon Usk",
            metier = "Développeur Go",
            specialite = "Spécialiste Backend",
            email = "melon.usk@example.com",
            disponible = false,
            description = "Développeur Go spécialisé en backend avec deux années d'expérience.",
            competences = listOf("Go", "PostgreSQL", "Docker", "API REST"),
            projets = listOf(
                Projet(id = 1, titre = "Happay, une API de paiement", description = "Service Go qui traite les transactions d'un commerce en ligne."),
                Projet(id = 2, titre = "Vauban, outil de monitoring", description = "Tableau de bord des performances d'un serveur hébergé sur AWS.")
            )
        )
    }
}