class ParcsController < ApplicationController
  def index

    @parceiros = Parceiro.all
    respond_to do |format|
      format.html
      format.json { render json: @parceiros }
    end


  end
end
