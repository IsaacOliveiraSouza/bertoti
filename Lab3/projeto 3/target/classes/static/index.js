i= 0
function uuidv4() {
    return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
      (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
  }

const btnAdd = document.getElementById('submitBtn')
function Adicionar() {
    axios.post('http://localhost:8080/animals', {
        id: inputID.value,
        name: nomeAnimal.value
    }) .then(response => {
        window.location.reload();
        console.log("Animal adicionado", response.data)
    }).catch(e => {
        }
    )
}
btnAdd.addEventListener('click',Adicionar)
document.addEventListener("DOMContentLoaded", () => {

    fetch("http://localhost:8080/animals") 
        .then(response => response.json())
        .then(data => {
            
            const animalList = document.getElementById("animal-list");
            data.forEach(animal => {
                const listItem = document.createElement("li");
                const deleteButton = document.createElement('button') 
                const upItem = document.createElement("input")
                const upButton = document.createElement("button");
                const inputID = document.getElementById('inputID')
                const nomeAnimal = document.getElementById('nomeAnimal')
                inputID.setAttribute('value',uuidv4())
                upButton.innerHTML = "Editar nome";
                deleteButton.innerHTML = "Excluir";
                listItem.textContent = animal.name;
                animalList.appendChild(listItem);
                animalList.appendChild(upItem);
                animalList.appendChild(upButton);
                animalList.appendChild(deleteButton);
                upButton.setAttribute('id',i);
                
                upButton.setAttribute('class','btn btn-primary')
                deleteButton.setAttribute('class','btn btn-danger')
                i++;
                upButton.addEventListener('click',Update)
                deleteButton.addEventListener('click', Excluir)
                function Update(){
                   upItemValor = upItem.value;
                   axios.put("http://localhost:8080/animals/"+animal.id , { id: animal.id, name: upItemValor })
                   .then(response => {
                    console.log("Animal atualizado com sucesso:", response.data)
                        listItem.innerText = upItemValor
                })
                .catch(error => {
                    console.error("Erro ao atualizar o animal:", error);
                });


                }

                function Excluir(){
                    axios.delete("http://localhost:8080/animals/"+animal.id, { id: animal.id })
                    .then(response =>{
                        console.log('Deletado', response.data)
                        upButton.remove()
                        deleteButton.remove()
                        listItem.remove()
                        upItem.remove()
                    })
                    .catch(error => {
                        console.error("Erro ao deletar", error)
                    })
                }

            });
        });
});

