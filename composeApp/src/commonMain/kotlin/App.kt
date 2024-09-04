import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch
import database.Estudiante
import database.EstudianteDao

@Composable
fun App(estudianteDao: EstudianteDao) {
    MaterialTheme {
        val estudiantes = remember { mutableStateOf<List<Estudiante>>(emptyList()) }
        val selectedEstudiante = remember { mutableStateOf<Estudiante?>(null) }
        val editingEstudiante = remember { mutableStateOf<Estudiante?>(null) }
        val scope = rememberCoroutineScope()
        val showForm = remember { mutableStateOf(false) }

        // Load data
        LaunchedEffect(Unit) {
            scope.launch {
                estudiantes.value = estudianteDao.obtenerTodos()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Estudiantes",
                    style = MaterialTheme.typography.h4
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(onClick = {
                    showForm.value = true
                }) {
                    Text(text = "Agregar")
                }
            }

            LazyColumn {
                items(estudiantes.value) { estudiante ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                selectedEstudiante.value = estudiante
                            }
                    ) {
                        Text(
                            text = estudiante.nombre,
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                        )
                        Divider(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }

        if (showForm.value) {
            AddStudentForm(
                onDismiss = { showForm.value = false },
                onSubmit = { estudiante ->
                    scope.launch {
                        estudianteDao.insertar(estudiante)
                        estudiantes.value = estudianteDao.obtenerTodos() // Refresh the list
                    }
                    showForm.value = false
                }
            )
        }

        selectedEstudiante.value?.let { estudiante ->
            StudentDetailsDialog(
                estudiante = estudiante,
                onDismiss = { selectedEstudiante.value = null },
                onEdit = { editingEstudiante.value = it }, // Configurar el estudiante para la edición
                onDelete = { id ->
                    scope.launch {
                        estudianteDao.eliminar(id)
                        estudiantes.value = estudianteDao.obtenerTodos() // Refresh the list
                    }
                    selectedEstudiante.value = null // Cerrar la ventana de detalles
                }
            )
        }

        editingEstudiante.value?.let { estudiante ->
            EditStudentForm(
                estudiante = estudiante,
                onDismiss = { editingEstudiante.value = null },
                onSubmit = { updatedEstudiante ->
                    scope.launch {
                        estudianteDao.actualizar(updatedEstudiante)
                        estudiantes.value = estudianteDao.obtenerTodos() // Refresh the list
                    }
                    editingEstudiante.value = null // Cerrar el formulario de edición
                    selectedEstudiante.value = null // Cerrar la ventana de detalles
                }
            )
        }
    }
}
