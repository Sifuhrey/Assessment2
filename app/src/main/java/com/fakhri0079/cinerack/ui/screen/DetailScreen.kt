package com.fakhri0079.cinerack.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fakhri0079.cinerack.R
import com.fakhri0079.cinerack.ui.theme.CineRackTheme
import com.fakhri0079.cinerack.util.ViewModelFactory

const val KEY_ID_FILM = "idFilm"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var rating by remember { mutableFloatStateOf(0f) }
    var isWatched by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getFilm(id) ?: return@LaunchedEffect
        title = data.title
        desc = data.desc
        rating = data.rating
        isWatched = data.isWatched
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null) {
                        Text(text = stringResource(id = R.string.tambah_film))
                    } else {
                        Text(text = stringResource(id = R.string.edit_film))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        if (title == "" || desc == "") {
                            Toast.makeText(context,R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null) {
                        viewModel.insert(title, desc, rating, isWatched)
                    }
                        else{
                            viewModel.update(id,title, desc, rating, isWatched)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null){
                        DeleteAction {
                            showDialog = true
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormFilm(
            title = title,
            onTitleChange = { title = it },
            desc = desc,
            onDescChange = { desc = it },
            rating = rating,
            onRatingChange = { rating = it },
            isWatched = isWatched,
            onIsWatchedChange = { isWatched = it },
            modifier = Modifier.padding(padding)
        )
        if (id != null && showDialog){
            DisplayAlertDialog(
                onDismissRequest = {showDialog = false}
            ) {
                showDialog = false
                viewModel.delete(id)
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun DeleteAction(delete: () -> Unit){
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}


@Composable
fun FormFilm(
    title: String, onTitleChange: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    rating: Float, onRatingChange: (Float) -> Unit,
    isWatched: Boolean, onIsWatchedChange: (Boolean) -> Unit,
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = { onDescChange(it) },
            label = { Text(text = stringResource(R.string.desc)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Slider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            value = rating,
            onValueChange = { onRatingChange(it) },

            valueRange = 0f..10f
        )
        Text(text = stringResource(R.string.score) + ": " + String.format("%.1f", rating))
        Switch(
            checked = isWatched,
            onCheckedChange = { onIsWatchedChange(it) }
        )
        Text(text = "Status: "+if (isWatched) stringResource(R.string.done) else stringResource(R.string.not_done))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    CineRackTheme {
        DetailScreen(rememberNavController())
    }
}
