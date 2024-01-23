/**
 * Handle click on buttons next to tasks
 * If button text is 'edit' then open input form to edit task
 * If button text is 'done' then send request to update task
 * @param e - click event
 * @returns {Promise<void>}
 */
const handleClick = async e => {
    const button = e.target;
    const index = parseInt(button.dataset.index);
    const text = button.innerHTML.trim();

    if (text === 'edit') {
        editTask(button, index);
    } else if (text === 'done') {
        await updateTask(button, index);
    }
}

/**
 * Open an input form to edit task
 * @param button - button next to task
 * @param index - index of task
 */
const editTask = (button, index) => {
    const span = document.querySelectorAll('.task-title').item(index);
    const input = document.createElement('input');
    input.type = 'text';
    input.name = 'title';
    input.className = 'task-title';
    input.value = button.dataset.title;
    input.required = true;

    span.replaceWith(input);
    button.innerHTML = 'done';
}

/**
 * Send request to update task and fetch updated task from server
 * @param button - button next to task
 * @param index - index of task
 * @returns {Promise<void>}
 */
const updateTask = async (button, index) => {
    const input = document.querySelectorAll('.task-title').item(index);
    const url = `/tasks/${button.dataset.id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            content: input.value
        })
    });
    const {task} = await response.json();

    const span = document.createElement('span');
    span.className = 'task-title';
    span.textContent = task.content;

    button.dataset.title = task.content;
    button.innerHTML = 'edit';

    input.replaceWith(span);
}

/**
 * Add event listener to buttons for editing tasks
 */
document.querySelectorAll('.edit-btn')
    .forEach(el => el.addEventListener('click', handleClick));

/**
 * Delete task when checkbox is checked
 * @param taskId - ID of task
 * @returns {Promise<void>}
 */
const deleteTask = async (taskId) => {
    const checkbox = window.event.target;

    if (checkbox.checked) {
        const url = `/tasks/${taskId}`;
        const res = await fetch(url, {
            method: 'DELETE'
        });

        if (res.ok) {
            location.reload();
        }
    }
}
