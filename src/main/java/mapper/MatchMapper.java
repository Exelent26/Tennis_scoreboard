package mapper;

import dto.OngoingMatch;
import model.Match;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "playerOne", source = "playerOne"),
            @Mapping(target = "playerTwo", source = "playerTwo"),
            @Mapping(target = "winner", expression = "java(src.findWinnerOfAMatch().orElse(null))")
    })
    Match toEntity(OngoingMatch src);
}