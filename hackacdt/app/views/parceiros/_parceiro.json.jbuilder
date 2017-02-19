json.extract! parceiro, :id, :nome, :endereco, :cep, :cidade, :estado, :created_at, :updated_at
json.url parceiro_url(parceiro, format: :json)