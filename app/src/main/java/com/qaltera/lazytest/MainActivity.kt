package com.qaltera.lazytest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qaltera.lazytest.data.TestDataBlock
import com.qaltera.lazytest.ui.theme.LazyTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    runTest("Android")
                }
            }
        }
    }
}

const val rowsCount = 35
const val columnsCount = 10

@Composable
fun runTest(name: String) {

    val lazyStateCol = rememberLazyListState()
    LazyColumn(
        state = lazyStateCol,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val dataList = arrayListOf<TestDataBlock>()
        for (i in 0 until rowsCount) {
            dataList.add(TestDataBlock(i.toString(), (0..columnsCount).toList()))
        }
        itemsIndexed(items = dataList,
            key = { index, item ->
                "$index"
            }
        ) { rowIndex, rowItem ->
            Log.d("MainActivity", "creating row $rowIndex")
            row(rowIndex, rowItem)
        }
    }
}

@Composable
fun row(index: Int, rowItem: TestDataBlock) {
    Text("$index \n\n\n\n\n\n\n")
    LazyRow {
        items(items = rowItem.data, key = { it }) { index ->
            Text("$index $index | ")
        }
    }
}