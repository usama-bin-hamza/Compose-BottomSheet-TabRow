package com.usama.tabrowbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.usama.tabrowbottomsheet.ui.theme.TabRowBottomSheetTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabRowBottomSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scaffoldState = rememberBottomSheetScaffoldState()
                    var isSheetOpen by rememberSaveable {
                        mutableStateOf(false)
                    }

                    val scope = rememberCoroutineScope()

                    val tabItems = remember {
                        mutableListOf(
                            TabItem(
                                title = "Home",
                                route = "home",
                                unSelectedIcon = Icons.Outlined.Home,
                                selectedIcon = Icons.Default.Home
                            ),
                            TabItem(
                                title = "Browser",
                                route = "browser",
                                unSelectedIcon = Icons.Outlined.ShoppingCart,
                                selectedIcon = Icons.Default.ShoppingCart
                            ),
                            TabItem(
                                title = "Account",
                                route = "account",
                                unSelectedIcon = Icons.Outlined.AccountCircle,
                                selectedIcon = Icons.Default.AccountCircle
                            ),
                        )
                    }

                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetContent = {
                            Image(
                                painter = painterResource(id = R.drawable.image),
                                contentDescription = "image",
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        },
                        sheetPeekHeight = 0.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center

                        ) {
                            Button(onClick = {
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                    isSheetOpen = true
                                }
                            }) {
                                Text(text = "Open Sheet")
                            }
                        }
                    }

                    var selectedTabItem by remember {
                        mutableStateOf(0)
                    }

                    var tabIndex by remember { mutableStateOf(0) }

                    Column(modifier = Modifier.fillMaxSize()) {
                        TabRow(selectedTabIndex = tabIndex) {
                            tabItems.forEachIndexed { index, tabItem ->
                                androidx.compose.material3.Tab(
                                    selected = selectedTabItem == index,
                                    onClick = {
                                        selectedTabItem = index
                                    },
                                    text = {
                                        Text(text = tabItem.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedTabItem) {
                                                tabItem.selectedIcon
                                            } else tabItem.unSelectedIcon,
                                            contentDescription = tabItem.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
