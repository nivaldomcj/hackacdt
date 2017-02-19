Rails.application.routes.draw do
  devise_for :users
  resources :acaos
  resources :organizacaos
  resources :parceiros
  root 'welcome#index'

  resources :voluntarios
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
