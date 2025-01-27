package com.abbas.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abbas.cats.data.model.CatResponse
import com.abbas.cats.ui.MainViewModel
import com.abbas.cats.ui.theme.CatsTheme
import com.abbas.cats.usecase.Result
import com.abbas.cats.usecase.presentationmodel.Cat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCats(1)
        enableEdgeToEdge()
        setContent {
            CatsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val data: Result<List<Cat>> by viewModel.catsDataStateFlow.collectAsStateWithLifecycle()
    if (data is Result.Success)
    Text(
        text = (data as Result.Success).data.toString(),
        modifier = modifier
    )
}