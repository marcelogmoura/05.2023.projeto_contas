<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Criar Usu�rios</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
</head>
<body>

  <div class="container my-5">
    <div class="row justify-content-center">
      <div class="col-lg-4">
        <div class="card">
          <div class="card-header text-center">
            <h2 class="card-title">Projeto Contas</h2>
            <h5>Criar Usu�rio</h5>
          </div>
          <div class="card-body">
          
		  <div class="m-3 text-center">
          <h5> ${mensagem}</h5>
          </div>
                              
            <form method="post" action="criar-usuario-post">
            
            	<div class="mb-3">
                <label for="nome" class="form-label">
                Nome do usu�rio:
                </label>
                <form:input 
                	path="dto.nome" 
                	type="text" 
                	class="form-control" 
                	id="nome" 
                	name="nome" 
                	required="required"
                	/>
              </div>
              
              <div class="mb-3">
                <label for="email" class="form-label">
                Email de acesso:
                </label>
                <form:input 
                path="dto.email" 
                type="email" 
                class="form-control" 
                id="email" 
                name="email"  
                required="required"
                />
              </div>
              
              <div class="mb-3">
                <label for="senha" class="form-label">
                Senha de acesso:
                </label>
                <form:input 
                path="dto.senha" 
                type="password" 
                class="form-control" 
                id="senha" 
                name="senha"  
                required="required"
                />
              </div>
              
              <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">
                Realizar Cadastro
                </button>
              </div>
              
            </form>
          </div>
          <div class="card-footer text-center">
            <a href="/projeto_contas/">Voltar</a> 
          </div>
          

          
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>



