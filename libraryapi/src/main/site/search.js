document.addEventListener('DOMContentLoaded', () => {
    const API_URL = 'http://localhost:8080/authors';
    const searchForm = document.getElementById('search-author-form');
    const resultsTableBody = document.querySelector('#search-results-table tbody');

    searchForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const name = document.getElementById('search-name').value;
        const nationality = document.getElementById('search-nationality').value;

        // Constrói a URL com os parâmetros de busca (query params)
        const query = new URLSearchParams();
        if (name) {
            query.append('name', name);
        }
        if (nationality) {
            query.append('nationality', nationality);
        }

        const fullUrl = `${API_URL}?${query.toString()}`;

        try {
            const response = await fetch(fullUrl);
            const authors = await response.json();

            resultsTableBody.innerHTML = ''; // Limpa resultados antigos

            if (authors.length === 0) {
                const row = document.createElement('tr');
                const cell = document.createElement('td');
                cell.setAttribute('colspan', 4);
                cell.textContent = 'Nenhum autor encontrado com os critérios fornecidos.';
                cell.style.textAlign = 'center';
                row.appendChild(cell);
                resultsTableBody.appendChild(row);
            } else {
                authors.forEach(author => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${author.id}</td>
                        <td>${author.name}</td>
                        <td>${author.birthDate}</td>
                        <td>${author.nationality}</td>
                    `;
                    resultsTableBody.appendChild(row);
                });
            }
        } catch (error) {
            console.error('Erro ao buscar autores:', error);
            resultsTableBody.innerHTML = '';
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            cell.setAttribute('colspan', 4);
            cell.textContent = 'Erro ao conectar com a API.';
            cell.style.textAlign = 'center';
            row.appendChild(cell);
            resultsTableBody.appendChild(row);
        }
    });

    // Dispara uma busca inicial para mostrar todos os autores ao carregar a página
    searchForm.dispatchEvent(new Event('submit'));
});