document.addEventListener('DOMContentLoaded', () => {
    const API_URL = 'http://localhost:8080/authors';
    const addAuthorForm = document.getElementById('add-author-form');
    const authorsTableBody = document.querySelector('#authors-table tbody');
    const editModal = document.getElementById('edit-modal');
    const editAuthorForm = document.getElementById('edit-author-form');
    const closeModal = document.querySelector('.close-button');
    const errorContainer = document.getElementById('error-container');

    // Função para exibir erros
    const displayErrors = (errors) => {
        errorContainer.innerHTML = ''; // Limpa erros anteriores
        const errorList = document.createElement('ul');
        errors.forEach(err => {
            const listItem = document.createElement('li');
            listItem.textContent = `${err.field}: ${err.error}`;
            errorList.appendChild(listItem);
        });
        errorContainer.appendChild(errorList);
        errorContainer.style.display = 'block';
    };

    // Função para limpar os erros
    const clearErrors = () => {
        errorContainer.innerHTML = '';
        errorContainer.style.display = 'none';
    };

    // Função para buscar e exibir os autores
    const fetchAuthors = async () => {
        try {
            const response = await fetch(API_URL);
            const authors = await response.json();
            authorsTableBody.innerHTML = '';
            authors.forEach(author => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${author.id}</td>
                    <td>${author.name}</td>
                    <td>${author.birthDate}</td>
                    <td>${author.nationality}</td>
                    <td class="action-buttons">
                        <button class="btn edit-btn" data-id="${author.id}">Editar</button>
                        <button class="btn delete-btn" data-id="${author.id}">Excluir</button>
                    </td>
                `;
                authorsTableBody.appendChild(row);
            });
        } catch (error) {
            console.error('Erro ao buscar autores:', error);
        }
    };

    // Adicionar novo autor
    addAuthorForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        clearErrors(); // Limpa erros antigos antes de uma nova submissão

        const newAuthor = {
            name: document.getElementById('name').value,
            birthDate: document.getElementById('birthDate').value,
            nationality: document.getElementById('nationality').value,
        };

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newAuthor),
            });

            // Verifica se a resposta foi bem-sucedida (status 2xx)
            if (response.ok) {
                fetchAuthors();
                addAuthorForm.reset();
            } else {
                // Se não foi, lê o corpo da resposta de erro
                const errorData = await response.json();
                if (errorData.fields) {
                    displayErrors(errorData.fields);
                } else {
                    // Para erros genéricos que não vêm com o campo 'fields'
                    displayErrors([{ field: 'Erro', error: errorData.message || 'Ocorreu um erro.' }]);
                }
            }
        } catch (error) {
            console.error('Erro de rede ao adicionar autor:', error);
            displayErrors([{ field: 'Sistema', error: 'Não foi possível conectar à API.' }]);
        }
    });

    // Abrir modal de edição e preencher formulário
    authorsTableBody.addEventListener('click', async (e) => {
        if (e.target.classList.contains('edit-btn')) {
            const id = e.target.dataset.id;
            try {
                const response = await fetch(`${API_URL}/${id}`);
                const author = await response.json();
                document.getElementById('edit-author-id').value = author.id;
                document.getElementById('edit-name').value = author.name;
                document.getElementById('edit-birthDate').value = author.birthDate;
                document.getElementById('edit-nationality').value = author.nationality;
                editModal.style.display = 'block';
            } catch (error) {
                console.error('Erro ao buscar dados do autor:', error);
            }
        }

        // Deletar autor
        if (e.target.classList.contains('delete-btn')) {
            const id = e.target.dataset.id;
            if (confirm('Tem certeza que deseja excluir este autor?')) {
                try {
                    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
                    fetchAuthors();
                } catch (error) {
                    console.error('Erro ao excluir autor:', error);
                }
            }
        }
    });

    // Fechar modal
    closeModal.addEventListener('click', () => {
        editModal.style.display = 'none';
    });

    window.addEventListener('click', (e) => {
        if (e.target == editModal) {
            editModal.style.display = 'none';
        }
    });

    // Salvar alterações do autor
    editAuthorForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = document.getElementById('edit-author-id').value;
        const updatedAuthor = {
            name: document.getElementById('edit-name').value,
            birthDate: document.getElementById('edit-birthDate').value,
            nationality: document.getElementById('edit-nationality').value,
        };

        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedAuthor),
            });

            if(response.ok) {
                editModal.style.display = 'none';
                fetchAuthors();
            } else {
                alert("Não foi possível atualizar o autor.");
            }

        } catch (error) {
            console.error('Erro ao atualizar autor:', error);
        }
    });

    // Carregar autores ao iniciar a página
    fetchAuthors();
});