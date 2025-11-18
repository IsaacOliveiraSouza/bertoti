// Função para gerar UUID v4
function uuidv4() {
    return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}

// Ícones de animais para exibição
const animalIcons = ['🦁', '🐯', '🐘', '🦒', '🐼', '🐨', '🦊', '🐺', '🐻', '🦓', '🦌', '🐪', '🦏', '🐊', '🦅', '🐧', '🦉', '🐍', '🦎', '🐢', '🐬', '🐳', '🦈', '🐙', '🦋', '🐝', '🐛', '🦗', '🐞', '🦂'];
let animalIndex = 0;

// Funções do Modal
function openModal() {
    const modal = document.getElementById('modal-overlay');
    const inputID = document.getElementById('inputID');
    const nomeAnimal = document.getElementById('nomeAnimal');
    
    inputID.value = uuidv4();
    nomeAnimal.value = '';
    nomeAnimal.focus();
    modal.style.display = 'flex';
}

function closeModal() {
    const modal = document.getElementById('modal-overlay');
    modal.style.display = 'none';
}

function closeModalOnOverlay(event) {
    if (event.target.id === 'modal-overlay') {
        closeModal();
    }
}

function openEditModal(animalId, animalName) {
    const modal = document.getElementById('edit-modal-overlay');
    const editNomeAnimal = document.getElementById('editNomeAnimal');
    const editAnimalId = document.getElementById('editAnimalId');
    
    editAnimalId.value = animalId;
    editNomeAnimal.value = animalName;
    editNomeAnimal.focus();
    modal.style.display = 'flex';
}

function closeEditModal() {
    const modal = document.getElementById('edit-modal-overlay');
    modal.style.display = 'none';
}

function closeEditModalOnOverlay(event) {
    if (event.target.id === 'edit-modal-overlay') {
        closeEditModal();
    }
}

// Função para adicionar animal
function adicionarAnimal() {
    const inputID = document.getElementById('inputID');
    const nomeAnimal = document.getElementById('nomeAnimal');
    const submitBtn = document.getElementById('submitBtn');
    
    if (!nomeAnimal.value.trim()) {
        nomeAnimal.style.borderColor = '#ef4444';
        nomeAnimal.focus();
        return;
    }
    
    submitBtn.textContent = 'Adicionando...';
    submitBtn.disabled = true;
    submitBtn.classList.add('loading');
    
    axios.post('http://localhost:8080/animals', {
        id: inputID.value,
        name: nomeAnimal.value.trim()
    })
    .then(response => {
        console.log("Animal adicionado", response.data);
        closeModal();
        loadAnimals();
        showNotification('Animal adicionado com sucesso!', 'success');
    })
    .catch(error => {
        console.error("Erro ao adicionar animal:", error);
        showNotification('Erro ao adicionar animal. Tente novamente.', 'error');
        submitBtn.textContent = 'Adicionar Animal';
        submitBtn.disabled = false;
        submitBtn.classList.remove('loading');
    });
}

// Função para atualizar animal
function atualizarAnimal(animalId, newName) {
    if (!newName.trim()) {
        showNotification('O nome não pode estar vazio!', 'error');
        return;
    }
    
    const editSubmitBtn = document.getElementById('editSubmitBtn');
    editSubmitBtn.textContent = 'Salvando...';
    editSubmitBtn.disabled = true;
    editSubmitBtn.classList.add('loading');
    
    axios.put(`http://localhost:8080/animals/${animalId}`, {
        id: animalId,
        name: newName.trim()
    })
    .then(response => {
        console.log("Animal atualizado com sucesso:", response.data);
        closeEditModal();
        loadAnimals();
        showNotification('Animal atualizado com sucesso!', 'success');
    })
    .catch(error => {
        console.error("Erro ao atualizar o animal:", error);
        showNotification('Erro ao atualizar animal. Tente novamente.', 'error');
        editSubmitBtn.textContent = 'Salvar Alterações';
        editSubmitBtn.disabled = false;
        editSubmitBtn.classList.remove('loading');
    });
}

// Função para excluir animal
function excluirAnimal(animalId, animalCard) {
    if (!confirm('Tem certeza que deseja excluir este animal?')) {
        return;
    }
    
    animalCard.classList.add('loading');
    
    axios.delete(`http://localhost:8080/animals/${animalId}`)
    .then(response => {
        console.log('Animal deletado', response.data);
        animalCard.style.animation = 'fadeOut 0.3s ease';
        setTimeout(() => {
            animalCard.remove();
            updateStats();
            checkEmptyState();
            showNotification('Animal excluído com sucesso!', 'success');
        }, 300);
    })
    .catch(error => {
        console.error("Erro ao deletar", error);
        animalCard.classList.remove('loading');
        showNotification('Erro ao excluir animal. Tente novamente.', 'error');
    });
}

// Função para criar card de animal
function createAnimalCard(animal) {
    const card = document.createElement('div');
    card.className = 'animal-card';
    card.dataset.animalId = animal.id;
    
    const icon = animalIcons[animalIndex % animalIcons.length];
    animalIndex++;
    
    card.innerHTML = `
        <span class="animal-icon">${icon}</span>
        <h3 class="animal-name">${escapeHtml(animal.name)}</h3>
        <div class="animal-actions">
            <button class="btn btn-edit" onclick="openEditModal('${animal.id}', '${escapeHtml(animal.name)}')">
                ✏️ Editar
            </button>
            <button class="btn btn-delete" onclick="excluirAnimal('${animal.id}', this.closest('.animal-card'))">
                🗑️ Excluir
            </button>
        </div>
    `;
    
    return card;
}

// Função para escapar HTML (prevenir XSS)
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Função para carregar animais
function loadAnimals() {
    const animalList = document.getElementById('animal-list');
    animalList.innerHTML = '';
    animalIndex = 0;
    
    fetch("http://localhost:8080/animals")
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                showEmptyState();
            } else {
                hideEmptyState();
                data.forEach(animal => {
                    const card = createAnimalCard(animal);
                    animalList.appendChild(card);
                });
            }
            updateStats();
        })
        .catch(error => {
            console.error("Erro ao carregar animais:", error);
            showNotification('Erro ao carregar animais. Verifique se o servidor está rodando.', 'error');
        });
}

// Função para atualizar estatísticas
function updateStats() {
    const animalList = document.getElementById('animal-list');
    const totalAnimals = animalList.children.length;
    document.getElementById('total-animals').textContent = totalAnimals;
}

// Função para verificar estado vazio
function checkEmptyState() {
    const animalList = document.getElementById('animal-list');
    if (animalList.children.length === 0) {
        showEmptyState();
    } else {
        hideEmptyState();
    }
}

function showEmptyState() {
    document.getElementById('empty-state').style.display = 'block';
}

function hideEmptyState() {
    document.getElementById('empty-state').style.display = 'none';
}

// Função para mostrar notificações
function showNotification(message, type = 'success') {
    // Remove notificação existente se houver
    const existing = document.querySelector('.notification');
    if (existing) {
        existing.remove();
    }
    
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.textContent = message;
    
    // Estilos inline para a notificação
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: ${type === 'success' ? '#10b981' : '#ef4444'};
        color: white;
        padding: 16px 24px;
        border-radius: 12px;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.5);
        z-index: 10000;
        animation: slideInRight 0.3s ease;
        font-weight: 600;
        max-width: 300px;
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.style.animation = 'slideOutRight 0.3s ease';
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 3000);
}

// Adicionar animações CSS para notificações
const style = document.createElement('style');
style.textContent = `
    @keyframes slideInRight {
        from {
            transform: translateX(100%);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOutRight {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(100%);
            opacity: 0;
        }
    }
    
    @keyframes fadeOut {
        from {
            opacity: 1;
            transform: scale(1);
        }
        to {
            opacity: 0;
            transform: scale(0.9);
        }
    }
`;
document.head.appendChild(style);

// Event Listeners
document.addEventListener("DOMContentLoaded", () => {
    // Botão de adicionar
    const submitBtn = document.getElementById('submitBtn');
    submitBtn.addEventListener('click', adicionarAnimal);
    
    // Botão de editar
    const editSubmitBtn = document.getElementById('editSubmitBtn');
    editSubmitBtn.addEventListener('click', () => {
        const editAnimalId = document.getElementById('editAnimalId').value;
        const editNomeAnimal = document.getElementById('editNomeAnimal').value;
        atualizarAnimal(editAnimalId, editNomeAnimal);
    });
    
    // Permitir adicionar com Enter
    const nomeAnimal = document.getElementById('nomeAnimal');
    nomeAnimal.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            adicionarAnimal();
        }
    });
    
    const editNomeAnimal = document.getElementById('editNomeAnimal');
    editNomeAnimal.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            const editAnimalId = document.getElementById('editAnimalId').value;
            atualizarAnimal(editAnimalId, editNomeAnimal.value);
        }
    });
    
    // Carregar animais ao iniciar
    loadAnimals();
});
