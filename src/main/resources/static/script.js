document.addEventListener('DOMContentLoaded', function() {
    fetchTodos();


    function fetchTodos() {
        fetch('http://localhost:8080/todos')  // Adjust if your endpoint is different
            .then(response => response.json())
            .then(todos => {
                const listElement = document.getElementById('todoList');
                console.log('FetchToDos ', todos);
                listElement.innerHTML = ''; // Clear existing entries
                todos.forEach(todo => {
                    const li = document.createElement('li');
                    li.className = 'list-group-item d-flex justify-content-between align-items-center';
                    li.textContent = todo.title + ' - Due: ' + (todo.completionDate || 'No Date');

                    const deleteBtn = document.createElement('button');
                    deleteBtn.className = 'btn btn-danger btn-sm';
                    deleteBtn.textContent = 'Delete';
                    deleteBtn.onclick = () => deleteTodo(todo.id);

                    li.appendChild(deleteBtn);
                    listElement.appendChild(li);
                });
            });
    }

    function deleteTodo(id) {
        fetch(`http://localhost:8080/todos/${id}`, { method: 'DELETE' })
            .then(response => {
            console.log('deleteTodos', response);
                if (response.ok) {
                    fetchTodos(); // Refresh the list after deletion
                } else {
                    alert('Failed to delete todo.');
                }
            });
    }
});