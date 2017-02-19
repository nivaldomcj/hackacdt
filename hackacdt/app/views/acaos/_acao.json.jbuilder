json.extract! acao, :id, :nome, :descricao, :dt_inicio, :dt_fim, :organizacao_id, :created_at, :updated_at
json.url acao_url(acao, format: :json)