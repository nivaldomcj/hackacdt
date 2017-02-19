class OngsController < ApplicationController

  def index
      @organizacaos = Organizacao.all
      respond_to do |format|
        format.html
        format.json { render json: @organizacaos }
      end
  end
end
