package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                println("Digite a idade do cachorro: ")
                val idadeCachorro = readLine()?.toIntOrNull() ?: 0
                println("Digite o nome do cachorro: ")
                val nomeCachorro = readLine() ?: ""
                val cachorro = Cachorro(ECor.azul, idadeCachorro)
                cachorro.nome = nomeCachorro
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                println("Digite a idade do gato: ")
                val idadeGato = readLine()?.toIntOrNull() ?: 0
                println("Digite o nome do gato: ")
                val nomeGato = readLine() ?: ""
                val gato = Gato(ECor.vermelho, idadeGato)
                gato.nome = nomeGato
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                println("Digite a idade do pássaro: ")
                val idadePassaro = readLine()?.toIntOrNull() ?: 0
                println("Digite o nome do pássaro: ")
                val nomePassaro = readLine() ?: ""
                val passaro = Passaro(ECor.branco, idadePassaro)
                passaro.nome = nomePassaro
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            6 -> {
                println("Insira o nome do animal que deseja remover:")
                val option = readlnOrNull() ?: ""
                repositorioAnimal.remover(option)
            }
            7 -> {
                println("Insira a cor dos animais que deseja listar:")
                val option = readlnOrNull()?.lowercase()
                var optionSelected = ECor.valueOf(option ?: "vermelho")
                repositorioAnimal.listarPorCor(optionSelected)
            }
            8 -> {
                println("Insira a idade dos animais que deseja listar:")
                val idade = readlnOrNull()?.toIntOrNull() ?: 0
                repositorioAnimal.listarPorIdade(idade)
            }
            9 -> {
                println("Digite o nome do animal: ")
                val nomeAnimal = readLine() ?: ""
                val animalEncontrado = repositorioAnimal.listarPorNome(nomeAnimal)
                if (animalEncontrado != null) {
                    println("Animal ${animalEncontrado.nome} foi encontrado.")
                } else {
                    println("Animal não encontrado.")
                }
            }
            10 -> {
                println("Digite o nome do homem: ")
                val nomeHomem = readLine() ?: ""
                println("Digite a idade do homem: ")
                val idadeHomem = readLine()?.toIntOrNull() ?: 0

                val homem = Homem(ECor.branco, idadeHomem)
                homem.nome = nomeHomem

                println("Cadastro de $nomeHomem criado com sucesso!")
            }
        }

    }
}

enum class ECor{
    vermelho, azul, branco
}

abstract class Animal(var cor: ECor, var idade: Int) {
    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int{
       return idade * 7
    }
}

class Cachorro(cor: ECor, idade: Int) : Animal(cor, idade) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}
class Gato(cor:ECor, idade: Int) : Animal(cor, idade) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(cor: ECor, idade: Int) : Animal(cor, idade) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

fun menu() {
    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir som")
    println("6 - Remover animal pelo nome")
    println("7 - Listar animal  pela cor")
    println("8 - Listar animal  pela idade")
    println("9 - Listar animal  por nome")
    println("10 - Criar Homem")
    println("11 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun listarPorIdade(idade:Int) {
        val idadeAnimal = animais.filter { it.idade == idade }
        if (idadeAnimal.isNotEmpty())
            idadeAnimal.forEach { println(it.nome) }
        else
            println("Não há animais com $idade anos de idade.")
    }

    fun listarPorCor(cor:ECor) {
        val corAnimal = animais.filter { it.cor == cor }
        if (corAnimal.isNotEmpty())
            corAnimal.forEach { println(it.nome) }
        else
            println("Não foi encontrado animais da cor $cor.")
    }

    fun remover(nomeAnimal: String){
        var animalRemovido = animais.find { it.nome.contains(nomeAnimal) }
        if(animais.remove(animalRemovido))
            println("Animal removido com sucesso!")
    }

    fun listarPorNome(nome: String): Animal? {
        return animais.find { it.nome.contains(nome) }
    }
}

class Homem(cor: ECor, idade: Int) : Animal(cor, idade) {

    override var nome: String = ""
        get() = "Homem: $field + $cor + ${idadeEmAnosHumanos()}"
        set(valor) {
            field = valor
        }

    override fun emitirSom() {
        println("Olá, eu sou um humano.")
    }

    override fun idadeEmAnosHumanos() : Int {
        return idade
    }
}

