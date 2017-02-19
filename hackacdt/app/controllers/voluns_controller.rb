class VolunsController < ApplicationController
  def index

    @voluntarios = Voluntario.all
    respond_to do |format|
      format.html
      format.json { render json: @voluntarios }

    end

  end
end
