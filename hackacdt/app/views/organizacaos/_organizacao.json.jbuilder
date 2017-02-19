json.extract! organizacao, :id, :nome, :area_atuacao, :endereco, :cep, :cidade, :estado, :created_at, :updated_at
json.url organizacao_url(organizacao, format: :json)